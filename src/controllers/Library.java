package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Library
{

    @FXML
    private void showEditForm() throws IOException
    {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("../views/editConstant.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Добавить константу");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showAddForm() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/addConstant.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Редактировать константу");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

}
