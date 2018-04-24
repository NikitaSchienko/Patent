package controllers;


import electret.Electret;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Controller
{
    @FXML
    private Button startModelingButton;

    @FXML
    private Button showModelButton;

    @FXML
    private ListView<String> resultListView;

    @FXML
    private TextField distanceElectrets;

    @FXML
    private TextField fieldDifference;

    @FXML
    private TextField radiusToDiametr;

    @FXML
    private TextField diametrElectret;

    @FXML
    private TextField chargeElectret;

    @FXML
    private TextField distancePointElectret;

    @FXML
    private LineChart chartOne;

    @FXML
    private TextField electretPenetration;


    @FXML
    private void createFileDoc() throws Exception
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/saveModel.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Сохранить модель");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void saveModel() throws Exception
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/saveModel.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Сохранить модель");
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    private void downloadModel(ActionEvent event) throws IOException
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File file = fileChooser.showOpenDialog(new Stage());

        System.out.println(file);


    }

    private void drawGraphic(double q, double E, double R, double d)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение E к d");


        for(double i = 0; i < d; i+=d/30)
        {
            double EE = Electret.tensionInPoint(q,E,R,d,i);

            series.getData().add(new XYChart.Data(String.valueOf(i),EE));
        }

        chartOne.getXAxis().setLabel("Расстояние до точки, x, м.");
        chartOne.getYAxis().setLabel("Напряженность, E");

        chartOne.getData().add(series);

        chartOne.setTitle("Отношение E к d");
    }

    @FXML
    public void showLibrary() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Controller.class.getResource("../views/library.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Библиотека");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void showModel() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/model.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Модель датчика");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void startModeling()
    {
        resultListView.getItems().clear();
        chartOne.getData().clear();
        //double q = 0.00000000000000000016;

        if(checkParameters())
        {

            double E = Electret.tensionInPoint(
                    Double.valueOf(chargeElectret.getText()),
                    Double.valueOf(electretPenetration.getText()),
                    Double.valueOf(diametrElectret.getText())/2,
                    Double.valueOf(distanceElectrets.getText()),
                    Double.valueOf(distancePointElectret.getText())
            );

            drawGraphic(Double.valueOf(chargeElectret.getText()),
                    Double.valueOf(electretPenetration.getText()),
                            Double.valueOf(diametrElectret.getText())/2,
                    Double.valueOf(distancePointElectret.getText()));

            resultListView.getItems().add("E = "+E);
//            resultListView.getItems().add("R = " + electret.radius() + " м");
//            resultListView.getItems().add("Delta = " + electret.difference() * 100 + "%");
        }
        else
        {
            resultListView.getItems().add("Некорректные параметры");
        }
    }



    private boolean checkParameters()
    {

        chargeElectret.setStyle("-fx-text-box-border: silver;");
        distanceElectrets.setStyle("-fx-text-box-border: silver;");
        diametrElectret.setStyle("-fx-text-box-border: silver;");
        distancePointElectret.setStyle("-fx-text-box-border: silver;");
        electretPenetration.setStyle("-fx-text-box-border: silver;");

        boolean check = true;
        try
        {
            double chargeElectret = Double.valueOf(this.chargeElectret.getText());

        }
        catch (Exception e)
        {
            this.chargeElectret.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            check = false;
        }
        try
        {
            if(Double.valueOf(distanceElectrets.getText()) <= 0)
            {
                distanceElectrets.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
                check = false;
            }
        }
        catch (Exception e)
        {
            distanceElectrets.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            check = false;
        }

        try
        {
            if (Double.valueOf(diametrElectret.getText()) <= 0) {
                diametrElectret.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
                check = false;
            }

        }
        catch (Exception e)
        {
            diametrElectret.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            check = false;
        }
        try
        {
            if (Double.valueOf(distancePointElectret.getText()) <= 0)
            {
                distancePointElectret.setStyle("-fx-text-box-border: red ;  -fx-focus-color: red ;");
                check = false;
            }
        }
        catch (Exception e)
        {
            distancePointElectret.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            check = false;
        }

        try
        {
            if (Double.valueOf(electretPenetration.getText()) <= 0)
            {
                electretPenetration.setStyle("-fx-text-box-border: red ;  -fx-focus-color: red ;");
                check = false;
            }
        }
        catch (Exception e)
        {
            electretPenetration.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            check = false;
        }

        return check;
    }

}
