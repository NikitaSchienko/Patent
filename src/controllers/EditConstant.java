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

import java.sql.SQLException;
import java.util.Map;

public class EditConstant
{
    private ObservableList<Constant> constants;
    private Constant currentConstant;
    private Procedure procedure;

    @FXML
    private ComboBox typeComboBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField valueField;

    @FXML
    private Button closeButton;

    private Library library;


    public void setObservableListConstants(ObservableList<Constant> constants, Library library, Constant currentConstant)
    {
        this.constants = constants;
        this.library = library;
        this.currentConstant = currentConstant;

        nameField.setText(currentConstant.getName());
        valueField.setText(String.valueOf(currentConstant.getValue()));


        procedure = SingletonType.getInstance().getProcedureType();
        Type type = (Type) procedure.getMap().get(currentConstant.getType());

        typeComboBox.getSelectionModel().select(type);
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
    private void saveEditDate() throws SQLException
    {
        if(conditionField())
        {
            constants.remove(currentConstant);

            Type type = (Type) typeComboBox.getSelectionModel().getSelectedItem();

            procedure = SingletonConstant.getInstance().getProcedureConstant();

            String name = nameField.getText();
            double value = Double.valueOf(valueField.getText());

            currentConstant.setName(name);
            currentConstant.setValue(value);
            currentConstant.setType(type.getId());
            currentConstant.setTypeName(type.getName());

            procedure.updateRecord(currentConstant);

            constants.addAll(currentConstant);
            library.initialize();
            System.out.println("Select = " + type.getName());

            closeEditForm();
        }
    }

    @FXML
    private void showListTypeConstant()
    {
        procedure = SingletonType.getInstance().getProcedureType();
        Map mapType = procedure.getMap();

        ObservableList<Object> observableList = FXCollections.observableArrayList(mapType.values().toArray());

        typeComboBox.setItems(observableList);
    }

    @FXML
    private void closeEditForm()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
