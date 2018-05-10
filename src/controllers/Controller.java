package controllers;


import database.Procedure;
import database.ProcedureConstant;
import database.ProcedureType;
import electret.Electret;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import reports.Report;
import singletons.SingletonConstant;
import singletons.SingletonType;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class Controller
{
    @FXML
    private Button startModelingButton;

    @FXML
    private Button showModelButton;

    @FXML
    private ListView<String> resultListView;

    @FXML
    private ComboBox distanceElectrets;

    @FXML
    private TextField fieldDifference;

    @FXML
    private TextField radiusToDiametr;

    @FXML
    private ComboBox diametrElectret;

    @FXML
    private ComboBox chargeElectret;

    @FXML
    private ComboBox distancePointElectret;

    @FXML
    private LineChart chartOne;

    @FXML
    private LineChart chartTwo;

    @FXML
    private ComboBox electretPenetration;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label loadLabel;


    @FXML
    private ComboBox M;

    @FXML
    private ComboBox Lf;

    @FXML
    private ComboBox l;

    @FXML
    private ComboBox p;

    private Procedure procedure;

    @FXML
    private void initialize() throws SQLException
    {
        procedure = new ProcedureConstant();

        diametrElectret.getEditor().setText("0.05");
        distanceElectrets.getEditor().setText("0.1");
        chargeElectret.getEditor().setText("0.00000000000000000016");
        electretPenetration.getEditor().setText("2.9");
        distancePointElectret.getEditor().setText("0.1");
        p.getEditor().setText("0.00000000000001");
        Lf.getEditor().setText("0.02");
        M.getEditor().setText("0.04");




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
            diametrElectret.getEditor().setText("");
            distanceElectrets.getEditor().setText("");
            chargeElectret.getEditor().setText("");
            electretPenetration.getEditor().setText("");
            distancePointElectret.getEditor().setText("");
            p.getEditor().setText("");
            Lf.getEditor().setText("");
            M.getEditor().setText("");

            resultListView.getItems().clear();
            chartOne.getData().clear();
            chartTwo.getData().clear();
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

        Report.saveReport(file);
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

    private void drawGraphicTwo()
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение delta_l к E");


        double Lf_double = Double.valueOf(Lf.getEditor().getText());
        double l_double = Double.valueOf(distanceElectrets.getEditor().getText());
        double M_double = Double.valueOf(M.getEditor().getText());

        double max_Lf = 0.01*Lf_double;

        for(double i = 0; i <= 20; i++)
        {
            double E = Electret.tensions(l_double, M_double, max_Lf*i/20);
            series.getData().add(new XYChart.Data(E,max_Lf/20*i));
        }

        chartTwo.getYAxis().setLabel("Дельта l");
        chartTwo.getXAxis().setLabel("Напряженность, E");

        chartTwo.getData().add(series);

        chartTwo.setTitle("Отношение delta_l к E");
    }

    private void drawGraphicOne(double q, double E, double R, double d)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение delta_l к E");


        for(double i = 0; i <= 20; i++)
        {
            double EE = Electret.tensionInPoint(q,E,R,d,d/20*i);

            series.getData().add(new XYChart.Data(d/20*i,EE));
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
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/model.fxml"));
        Parent root = loader.load();

        //Parent root = FXMLLoader.load(getClass().getResource("../views/model.fxml"));
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
        chartTwo.getData().clear();
        //double q = 0.00000000000000000016;

//        if(checkParameters())
//        {

            double E = Electret.tensionInPoint(
                    Double.valueOf(chargeElectret.getEditor().getText()),
                    Double.valueOf(electretPenetration.getEditor().getText()),
                    Double.valueOf(diametrElectret.getEditor().getText())/2,

                    Double.valueOf(distanceElectrets.getEditor().getText()),
                    Double.valueOf(distancePointElectret.getEditor().getText())
            );
            double F = Electret.force(Double.valueOf(p.getEditor().getText()),
                    Double.valueOf(electretPenetration.getEditor().getText()),
                    Double.valueOf(distanceElectrets.getEditor().getText()));

            drawGraphicOne(Double.valueOf(chargeElectret.getEditor().getText()),
                    Double.valueOf(electretPenetration.getEditor().getText()),
                            Double.valueOf(diametrElectret.getEditor().getText())/2,
                    Double.valueOf(distanceElectrets.getEditor().getText()));

            drawGraphicTwo();


            resultListView.getItems().add("E = "+E);
            resultListView.getItems().add("F = "+F);
//            resultListView.getItems().add("R = " + electret.radius() + " м");
//            resultListView.getItems().add("Delta = " + electret.difference() * 100 + "%");
//        }
//        else
//        {
//            resultListView.getItems().add("Некорректные параметры");
//        }
    }



    /*private boolean checkParameters()
    {

        chargeElectret.setStyle("-fx-text-box-border: silver;");
        distanceElectrets.setStyle("-fx-text-box-border: silver;");
        diametrElectret.setStyle("-fx-text-box-border: silver;");
        distancePointElectret.setStyle("-fx-text-box-border: silver;");
        electretPenetration.setStyle("-fx-text-box-border: silver;");

        boolean check = true;
        try
        {
            double chargeElectret = Double.valueOf(this.chargeElectret.getSelectionModel().getSelectedItem().toString());

        }
        catch (Exception e)
        {
            this.chargeElectret.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            check = false;
        }
        try
        {
            if(Double.valueOf(distanceElectrets.getSelectionModel().getSelectedItem().toString()) <= 0)
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
            if (Double.valueOf(diametrElectret.getSelectionModel().getSelectedItem().toString()) <= 0) {
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
            if (Double.valueOf(distancePointElectret.getSelectionModel().getSelectedItem().toString()) <= 0)
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
            if (Double.valueOf(electretPenetration.getSelectionModel().getSelectedItem().toString()) <= 0)
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
    }*/


    @FXML
    public void getListDiametrElectret() throws SQLException
    {

        Map mapConstant = procedure.selectRecords(1000);

        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());

        diametrElectret.setItems(observableList);
    }


    @FXML
    public  void getListDistanceElectrets() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1001);

        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());

        distanceElectrets.setItems(observableList);
    }


    @FXML
    public void getListChargeElectret() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1002);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        chargeElectret.setItems(observableList);
    }

    @FXML
    public void getListElectretPenetration() throws  SQLException
    {
        Map mapConstant = procedure.selectRecords(1003);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        electretPenetration.setItems(observableList);
    }

    @FXML
    public void getListDistancePointElectret() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1004);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        distancePointElectret.setItems(observableList);
    }

    @FXML
    public void getM() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1007);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        M.setItems(observableList);
    }


    @FXML
    public void getLf() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1006);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        Lf.setItems(observableList);
    }

    @FXML
    public void getP() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1005);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        p.setItems(observableList);
    }
}
