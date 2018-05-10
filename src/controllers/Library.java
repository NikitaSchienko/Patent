package controllers;

import database.Procedure;
import database.ProcedureConstant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Constant;
import singletons.SingletonConstant;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class Library
{
    private ObservableList<Constant> constants;
    private Procedure procedure;
    private ProgressIndicator progressIndicator;

    @FXML
    private TableView tableConstant;

    @FXML
    private TableColumn<Constant, Integer> columnId;

    @FXML
    private TableColumn<Constant, String> columnName;

    @FXML
    private TableColumn<Constant, String> columnType;

    @FXML
    private TableColumn<Constant, Double> columnValue;


    public void setProgressIndicator(ProgressIndicator progressIndicator)
    {
        this.progressIndicator = progressIndicator;
        progressIndicator.setVisible(false);
        //loadLabel.setVisible(false);

    }

    @FXML
    private void showEditForm() throws IOException
    {
        Constant currentConstant = (Constant) tableConstant.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/editConstant.fxml"));
        Parent root = loader.load();

        EditConstant editConstant = loader.getController();

        editConstant.setObservableListConstants(constants, this, currentConstant);


        Scene scene = new Scene(root);
        stage.setTitle("Редактировать константу");
        stage.getIcons().add(new Image("views/image/pen.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void doubleClickOnRow(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            try
            {
                showEditForm();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void showAddForm() throws IOException
    {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addConstant.fxml"));
        Parent root = loader.load();

        //Parent root = FXMLLoader.load(getClass().getResource("../views/addConstant.fxml"));
        AddConstant addConstant = loader.getController();
        addConstant.setObservableListConstants(constants, this);

        Scene scene = new Scene(root);

        stage.setTitle("Добавить константу");
        stage.getIcons().add(new Image("views/image/pen.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteConstant() throws SQLException
    {
        try
        {
            Constant constant = (Constant) tableConstant.getSelectionModel().getSelectedItem();
//            if(constant == null)
//            {
//                throw new Exception();
//            }


            procedure.deleteRecord(constant);
            constants.remove(constant);
            //procedure.getMap().remove(constant);
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Ошибка удаления");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка! Удаление невозможно!");

            alert.showAndWait();
            e.printStackTrace();
        }

    }


    @FXML
    public void initialize() throws SQLException
    {
            procedure = new ProcedureConstant();//SingletonConstant.getInstance().getProcedureConstant();
            Map<Integer, Constant> mapConstant = procedure.getMap();
            constants = FXCollections.observableArrayList(mapConstant.values());

            columnName.setCellValueFactory(new PropertyValueFactory<Constant,String>("name"));
            columnId.setCellValueFactory(new PropertyValueFactory<Constant, Integer>("id"));
            columnType.setCellValueFactory(new PropertyValueFactory<Constant, String>("typeName"));
            columnValue.setCellValueFactory(new PropertyValueFactory<Constant, Double>("value"));


            tableConstant.setItems(constants);
    }
}
