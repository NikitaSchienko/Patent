package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;



import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;

public class SaveModel
{
    @FXML
    private TextField nameTextField;

    @FXML
    private Button closeButton;

    @FXML
    private void saveModelDoc() throws Exception
    {
        String nameFile = nameTextField.getText();

        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\amitabh\\Pictures\\pics\\"+nameFile + ".model"));

        fos.close();

    }

    @FXML
    private void closeWindow() throws Exception
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
