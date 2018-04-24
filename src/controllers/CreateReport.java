package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;

public class CreateReport
{
    @FXML
    private TextField nameTextField;

    @FXML
    private Button closeButton;

    @FXML
    private void saveModelDoc() throws Exception
    {
        String nameFile = nameTextField.getText();

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph tmpParagraph = document.createParagraph();
        XWPFRun tmpRun = tmpParagraph.createRun();
        tmpRun.setText("Датчик - \""+nameFile+"\"");
        tmpRun.setFontSize(18);

        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\amitabh\\Pictures\\pics\\"+nameFile + ".doc"));
        document.write(fos);
        fos.close();

    }

    @FXML
    private void closeWindow() throws Exception
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
