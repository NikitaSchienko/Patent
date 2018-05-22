package controllers;

import database.Procedure;
import database.ProcedureConstant;
import electret.Electret;
import electret.FormulasOutputMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.InputDateModel;
import pojo.ModelOutputMode;
import reports.Report;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ControllerOutputMode
{
    private ObservableList observableList;

    private Double EPSILON_CONST = 0.5;

    @FXML
    private Button startModelingButton;

    @FXML
    private Button showModelButton;

    @FXML
    private TableView tableResult;

    @FXML
    private TableColumn columnu;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label loadLabel;

    @FXML
    public ComboBox dComboBox;

    @FXML
    public ComboBox EpsilonComboBox;

    @FXML
    public ComboBox pComboBox;

    @FXML
    private TableColumn columnGraphic;

    @FXML
    public TableColumn columnNumber;

    @FXML
    public TableColumn columnF;

    @FXML
    public TableColumn columnU;

    @FXML
    public TableColumn columnl1;

    @FXML
    public TableColumn columnl2;

    @FXML
    public TableColumn columnl3;

    @FXML
    public TableColumn columnR;

    @FXML
    public TableColumn columnL;

    @FXML
    public TableColumn columnE;

    @FXML
    public ComboBox lamdaResultComboBox;

    @FXML
    public ComboBox lamdaComboBox;

    @FXML
    public LineChart chartOne;

    @FXML
    public ComboBox MComboBox;

    @FXML
    public LineChart chartThree;

    @FXML
    public LineChart chartTwo;

    @FXML
    public LineChart chartFive;

    @FXML
    public LineChart chartFour;

    @FXML
    public ComboBox neffComboBox;

    private Procedure procedure;

    @FXML
    private void initialize() throws SQLException
    {
        dComboBox.getEditor().setText("0.05");
        neffComboBox.getEditor().setText("0.4447");
        pComboBox.getEditor().setText("2.2E-30");
        EpsilonComboBox.getEditor().setText("0.02");
        MComboBox.getEditor().setText("0.04");
        lamdaResultComboBox.getEditor().setText("2.5E-10");
        lamdaComboBox.getEditor().setText("2.1E-9");

    }

    @FXML
    private void clearAllField()
    {
        ButtonType no = new ButtonType("Нет", ButtonBar.ButtonData.NO);
        ButtonType yes = new ButtonType("Да", ButtonBar.ButtonData.YES);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Вы действительно хотите очистить все поля ввода?", yes, no);

        alert.setTitle("Очистка");

        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("views/image/clear.png"));
        alert.showAndWait();

        if (alert.getResult().getText().equals("Да"))
        {

        }
    }

    @FXML
    private void createFileDoc() throws Exception
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Создать отчёт");


        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]
                {
                        new FileChooser.ExtensionFilter("Text Files", new String[]{"*.docx"}),
                        new FileChooser.ExtensionFilter("DOCX", new String[]{"*.docx"})
                });

        File file = fileChooser.showSaveDialog(new Stage());
        //заполнение файла

        //Report.saveReport(file, null);
        //file.createNewFile();
        System.out.println(file);

    }

    @FXML
    private void saveModel() throws Exception
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");


        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]
                {
                        new FileChooser.ExtensionFilter("Image Files", new String[]{"*.model"}),
                        new FileChooser.ExtensionFilter("MODEL", new String[]{"*.model"})
                });

        File file = fileChooser.showSaveDialog(new Stage());
        //заполнение файла
        file.createNewFile();
        System.out.println(file);
    }


    @FXML
    private void downloadModel(ActionEvent event) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");


//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]
//                {
//                        new FileChooser.ExtensionFilter("Image Files", new String[]{"*.model"}),
//                        new FileChooser.ExtensionFilter("MODEL", new String[]{"*.model"})
//                });

        File file = fileChooser.showOpenDialog(new Stage());
        //заполнение файла
        //file.createNewFile();
        System.out.println(file);
    }



    @FXML
    public void showLibrary() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/library.fxml"));
        Parent root = loader.load();
        Library library = loader.getController();
        library.setProgressIndicator(progressIndicator);
        //Parent root = FXMLLoader.load(Controller.class.getResource("../views/library.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("views/image/books.png"));
        stage.setTitle("Библиотека");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void showModel() throws IOException
    {
        chartOne.getData().clear();
        chartTwo.getData().clear();
        chartThree.getData().clear();
        chartFour.getData().clear();
        chartFive.getData().clear();


        for(int i = 0; i < observableList.size(); i++)
        {
            //ModelOutputMode selectRow = (ModelOutputMode) tableResult.getSelectionModel().getSelectedItem();
            ModelOutputMode selectRow = (ModelOutputMode) observableList.get(i);

            if(selectRow.getCheck().isSelected() == true)
            {

                double p_ = Double.valueOf(pComboBox.getEditor().getText());
                double eps = Double.valueOf(EpsilonComboBox.getEditor().getText());
                double lamda1 = Double.valueOf(lamdaComboBox.getEditor().getText());
                double lamda2 = Double.valueOf(lamdaResultComboBox.getEditor().getText());
                double d = Double.valueOf(dComboBox.getEditor().getText());
                double neff = Double.valueOf(neffComboBox.getEditor().getText());

                double R = selectRow.getR();
                double l1 = selectRow.getL1();
                double l2 = selectRow.getL2();
                double l3 = selectRow.getL3();
                double U = selectRow.getU();

                double E = Electret.tension(R, eps, l2/2, l1, l2, l3, p_, U);
                double M = Double.valueOf(MComboBox.getEditor().getText());
                double l = FormulasOutputMode.deltaL(M, E, l2);

                double F = FormulasOutputMode.force(eps, E, d);
                double period = FormulasOutputMode.periodVBR(lamda1,neff);



                drawGraphicOne(i+1,U,p_,eps,R,l1,l2,l3,l2);
                drawGraphicTwo(i+1,U,p_,eps,R,l1,l2,l3,M);
                //drawGraphicThree(l2,R);
                drawGraphicThree(i+1,R,eps,l2/2,l1,l2,l3,p_,U,M);
                drawGraphicFour(i+1,R,eps,l2/2,l1,l2,l3,p_,U);
                drawGraphicFive(i+1,period, neff,  R,  eps,  l2/2,  l1,  l2,  l3,  p_,  U,  M);
            }
        }

//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/model.fxml"));
//        Parent root = loader.load();
//
//        //Parent root = FXMLLoader.load(getClass().getResource("../views/model.fxml"));
//        Scene scene = new Scene(root);
//        stage.getIcons().add(new Image("views/image/cirkul.png"));
//        stage.setTitle("Модель датчика");
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    public void startModeling()
    {
        tableResult.getItems().clear();

        Random random = new Random();

        double p_ = Double.valueOf(pComboBox.getEditor().getText());
        double eps = Double.valueOf(EpsilonComboBox.getEditor().getText());
        double lamda1 = Double.valueOf(lamdaComboBox.getEditor().getText());
        double lamda2 = Double.valueOf(lamdaResultComboBox.getEditor().getText());
        double d = Double.valueOf(dComboBox.getEditor().getText());
        double M = Double.valueOf(MComboBox.getEditor().getText());
        double neff = Double.valueOf(neffComboBox.getEditor().getText());
        double period = FormulasOutputMode.periodVBR(lamda1,neff);

        ArrayList<ModelOutputMode> arrayList = new ArrayList<ModelOutputMode>();

        double resE = Math.sqrt(FormulasOutputMode.deformation(lamda1,lamda2)/M);

        for(int i = 0; i < 100; i++)
        {
            double R = (double) (random.nextInt(300) + 100) / 100000;
            double l1 = (double) (random.nextInt(100) + 10) / 100000;
            double l2 = (double) (random.nextInt(300) + 500) / 100000;
            double l3 = (double) (random.nextInt(200) + 50) / 100000;
            double U = (double) (random.nextInt(800) + 100) / 10;

            double E = Electret.tension(R, eps, l2/2, l1, l2, l3, p_, U);



//            System.out.println("R = " + R);
//            System.out.println("l1 = " + l1);
//            System.out.println("l2 = " + l2);
//            System.out.println("l3 = " + l3);
//
//            System.out.println("F="+F);
//            System.out.println("l="+l);

            System.out.println("E = "+E+" and resE = "+resE);
            if(Math.abs(E-resE)<EPSILON_CONST)
            {
                double l = FormulasOutputMode.deltaL(M,E,l2);
                double F = FormulasOutputMode.force(eps,E,d);
                double u = FormulasOutputMode.deformation(lamda1,lamda2);

                ModelOutputMode modelOutputMode = new ModelOutputMode(i + 1, l1, l2, l3, R, U, l, E, F, u, new CheckBox());
                arrayList.add(modelOutputMode);
            }

        }

        observableList = FXCollections.observableArrayList(arrayList);


        columnGraphic.setCellValueFactory(new PropertyValueFactory<ModelOutputMode,CheckBox>("check"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<ModelOutputMode,Integer>("number"));
        columnE.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("E"));
        columnl1.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l1"));
        columnl2.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l2"));
        columnl3.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l3"));
        columnF.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("F"));
        columnU.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("U"));
        columnR.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("R"));
        columnL.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l"));
        columnu.setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("u"));

        tableResult.setItems(observableList);

    }


    private void drawGraphicTwo(int N, double U,double p_, double eps, double R, double l1, double l2, double l3, double M)
    {


        XYChart.Series series = new XYChart.Series();

        series.setName("Датчик №"+N);

        double E = Electret.tension(R,eps,l2/2,l1,l2,l3,p_,U);

        for(double i = 0; i <= E; i+=E/20)
        {
            double delta = Electret.delta_l(l2,M,i);
            series.getData().add(new XYChart.Data(i/Math.pow(10,9),delta/Math.pow(10,24)));
        }

        chartTwo.getYAxis().setLabel("Удлинение релаксора l, им");
        chartTwo.getXAxis().setLabel("Напряженность, E, нВ/м");

        chartTwo.getData().add(series);

        chartTwo.setTitle("Отношение удлинения релаксора к E");
    }

    private void drawGraphicOne(int N,double U,double p_, double eps, double R, double l1, double l2, double l3, double x)
    {

        XYChart.Series series = new XYChart.Series();

        series.setName("График №"+N);


        for(double i = 0; i <= l2; i+=l2/20)
        {
            double E = Electret.tension(R,eps,i,l1,l2,l3,p_,U)*Math.pow(10,9);
            System.out.println(E);
            //double E = Electret.tensionInPoint(q,eps,R,l2,i);
            series.getData().add(new XYChart.Data(i,E));
        }

        chartOne.getXAxis().setLabel("Расстояние до точки, x, м.");
        chartOne.getYAxis().setLabel("Напряженность, E, нВ/м");

        chartOne.getData().add(series);

        chartOne.setTitle("Отношение E к d");
    }


    private void drawGraphicThree(int N,double R, double eps, double x, double l1, double l2, double l3, double p_, double U, double M)
    {


        XYChart.Series series = new XYChart.Series();

        series.setName("График №"+N);


        double E = Electret.tension(R,eps,x,l1,l2,l3,p_,U);

        for(double i = 0; i <= E; i+=E/20)
        {
            double u = Electret.deformation(M,i);
            series.getData().add(new XYChart.Data(i*Math.pow(10,9),u*Math.pow(10,21)));
        }

        chartThree.getXAxis().setLabel("Напряженность, E, нВ/м");
        chartThree.getYAxis().setLabel("Деформация, u, 10ˉ²¹");
        chartThree.getData().add(series);

        chartThree.setTitle("Отношение E к u");
    }

    private void drawGraphicFive(int N,double period, double neff, double R, double eps, double x, double l1, double l2, double l3, double p_, double U, double M)
    {


        XYChart.Series series = new XYChart.Series();

        series.setName("График №"+N);

        System.out.println("lamda of U");
        for(double i = 0; i <= U; i+=U/20)
        {
            double E = Electret.tension(R,eps,x,l1,l2,l3,p_,i);
            //System.out.println(E);
            double u = Electret.deformation(M,E);
            //System.out.println(u);
            double lamda = Electret.lengthWave(neff, period*u);
            //lamda = lamda*Math.pow(10,32);
            //System.out.println(lamda);
            series.getData().add(new XYChart.Data(i,lamda*Math.pow(10,21)));
        }

        chartFive.getXAxis().setLabel("Напряжение, U, В");
        chartFive.getYAxis().setLabel("Длина волны, λ, им");
        chartFive.getData().add(series);

        chartFive.setTitle("Отношение длины волны к U");
    }

    private void drawGraphicFour(int N, double R, double eps, double x, double l1, double l2, double l3, double p_, double U)
    {


        XYChart.Series series = new XYChart.Series();

        series.setName("График №"+N);


        for(double i = 0; i <= U; i+=U/20)
        {
            double E = Electret.tension(R,eps,x,l1,l2,l3,p_,i);
            series.getData().add(new XYChart.Data(i,E*Math.pow(10,9)));
        }

        chartFour.getYAxis().setLabel("Напряженность, E, нВ/м");
        chartFour.getXAxis().setLabel("Напряжение, U, В");

        chartFour.getData().add(series);

        chartFour.setTitle("Отношение E к U");
    }




    @FXML
    public void showOutputMode() throws IOException
    {
//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/outputMode.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.getIcons().add(new Image("views/image/application.png"));
//        stage.setTitle("Моделирование датчика по выходным параметрам");
//        stage.setScene(scene);
//        stage.show();
//
//        close();
    }

    @FXML
    public void showInputMode() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/inputMode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("views/image/application.png"));
        stage.setTitle("Моделирование датчика по входным параметрам");
        stage.setWidth(1250);
        stage.setHeight(1000);
        stage.setScene(scene);
        stage.show();

        close();
    }

    @FXML
    public void close()
    {
        Stage stage = (Stage) startModelingButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void showInputModelOnPatent(MouseEvent event) throws IOException
    {
        if (event.getClickCount() == 2)
        {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/inputMode.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.setParameters(getInputDateModel());
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("views/image/application.png"));
            stage.setTitle("Моделирование датчика по входным параметрам");
            stage.setWidth(1250);
            stage.setHeight(1000);
            stage.setScene(scene);
            stage.show();
        }
    }

    private InputDateModel getInputDateModel()
    {
        ModelOutputMode selectRow = (ModelOutputMode) tableResult.getSelectionModel().getSelectedItem();

        double p_ = Double.valueOf(pComboBox.getEditor().getText());
        double eps = Double.valueOf(EpsilonComboBox.getEditor().getText());
        double lamda1 = Double.valueOf(lamdaComboBox.getEditor().getText());
        double lamda2 = Double.valueOf(lamdaResultComboBox.getEditor().getText());
        double d = Double.valueOf(dComboBox.getEditor().getText());
        double M = Double.valueOf(MComboBox.getEditor().getText());
        double neff = Double.valueOf(neffComboBox.getEditor().getText());
        double period = FormulasOutputMode.periodVBR(lamda1,neff);

        double R = selectRow.getR();
        double l1 = selectRow.getL1();
        double l2 = selectRow.getL2();
        double l3 = selectRow.getL3();
        double U = selectRow.getU();



        InputDateModel inputDateModel = new InputDateModel();

        inputDateModel.setdElectret(R*2);
        inputDateModel.setEpsilon(eps);
        inputDateModel.setL2(l2);
        inputDateModel.setL3(l3);
        inputDateModel.setL1(l1);
        inputDateModel.setP_(p_);
        inputDateModel.setU(U);
        inputDateModel.setM(M);
        inputDateModel.setD(d);
        inputDateModel.setLf(2*l1+2*l1+l2+0.005);
        inputDateModel.setNeff(neff);
        inputDateModel.setPeriod(period);

        return inputDateModel;
    }
}
