package controllers;

import database.Database;
import database.Procedure;
import database.ProcedureConstant;
import database.ProcedureType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Constant;
import pojo.Type;
import singletons.SingletonConstant;
import singletons.SingletonType;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Map;

public class AddConstant
{
    private Procedure procedure;
    private ObservableList<Constant> constants;

    @FXML
    private ComboBox typeComboBox;

    @FXML
    private Button closeButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField valueField;

    private Library library;

    public void setObservableListConstants(ObservableList<Constant> constants, Library library)
    {
        this.constants = constants;
        this.library = library;
    }

    @FXML
    private void addConstant() throws SQLException
    {

        if(conditionField())
        {
            Type type = (Type) typeComboBox.getSelectionModel().getSelectedItem();
            procedure = new ProcedureConstant();

            Integer id = Database.newId();
            String name = nameField.getText();
            double value = Double.valueOf(valueField.getText());

            Constant constant = new Constant(id, name, value, type.getId(), type.getName());

            procedure.insertRecord(constant);
            constants.add(constant);

            System.out.println("Select = " + type.getName());
            library.initialize();
            closeAddForm();
        }

    }

    private boolean conditionField()
    {
        boolean flag = true;

        nameField.setStyle("-fx-border-color: none;");
        typeComboBox.setStyle("-fx-border-color: none;");
        valueField.setStyle("-fx-border-color: none;");


        if(nameField.getText().length() == 0)
        {
            nameField.setStyle("-fx-border-color: red ; -fx-focus-color: red;");
            flag = false;
        }
        if(valueField.getText().length() == 0)
        {
            valueField.setStyle("-fx-border-color: red ; -fx-focus-color: red;");
            flag = false;
        }
        else
        {
            try
            {
                Double value = Double.valueOf(valueField.getText());
            }
            catch (Exception e)
            {
                valueField.setStyle("-fx-border-color: red ; -fx-focus-color: red;");
                flag = false;
            }
        }
        if(typeComboBox.getSelectionModel().getSelectedItem() == null)
        {
            typeComboBox.setStyle("-fx-border-color: red;  -fx-focus-color: red;");
            flag = false;
        }


        return flag;
    }

    @FXML
    private void showListTypeConstant() throws SQLException
    {

        ProcedureType procedureType = new ProcedureType();
        Map mapType = procedureType.getMap();

        ObservableList<Object> observableList = FXCollections.observableArrayList(mapType.values().toArray());

        typeComboBox.setItems(observableList);

    }

    @FXML
    private void closeAddForm()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
