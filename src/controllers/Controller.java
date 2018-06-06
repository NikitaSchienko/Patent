package controllers;


import database.Procedure;
import database.ProcedureConstant;
import database.ProcedureType;
import electret.Electret;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.InputDateModel;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import pojo.DataRoport;
import reports.Report;
import singletons.SingletonConstant;
import singletons.SingletonType;
import xml.ProcedureModel;


import javax.imageio.ImageIO;
import java.awt.*;
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
    private ComboBox dElectretComboBox;

    @FXML
    private ComboBox qComboBox;

    @FXML
    private ComboBox distancePointElectret;

    @FXML
    private LineChart chartOne;

    @FXML
    private LineChart chartTwo;

    @FXML
    private LineChart chartThree;

    @FXML
    private LineChart chartFive;


    @FXML
    private ComboBox epsilonComboBox;

    @FXML
    private ProgressBar progress;

    @FXML
    private Label loadLabel;

    @FXML
    private ComboBox MComboBox;

    @FXML
    private ComboBox LfComboBox;


    @FXML
    private ComboBox p_ComboBox;

    @FXML
    private ComboBox l1ComboBox;

    @FXML
    private ComboBox l2ComboBox;

    @FXML
    private ComboBox dCabelComboBox;

    @FXML
    private ComboBox UComboBox;

    @FXML
    private ComboBox l3ComboBox;

    @FXML
    private ComboBox periodComboBox;

    @FXML
    private ComboBox neffComboBox;

    private DataRoport dataRoport;

    @FXML
    private LineChart chartFour;

    private Procedure procedure;

    private InputDateModel inputDateModel;

    @FXML
    private void initialize() throws SQLException
    {
        inputDateModel = new InputDateModel();
        procedure = new ProcedureConstant();

        dataRoport = new DataRoport();

        dElectretComboBox.getEditor().setText("0.025");
        epsilonComboBox.getEditor().setText("2.9");
        l2ComboBox.getEditor().setText("0.02");
        l3ComboBox.getEditor().setText("0.005");
        l1ComboBox.getEditor().setText("0.0015");
        p_ComboBox.getEditor().setText("3.95E-30");
        UComboBox.getEditor().setText("22.5");
        MComboBox.getEditor().setText("0.04");
        dCabelComboBox.getEditor().setText("0.017");
        LfComboBox.getEditor().setText("0.075");
        neffComboBox.getEditor().setText("1.4447");
        periodComboBox.getEditor().setText("6.7E-4");
    }

    public void setParameters(InputDateModel inputDateModel)
    {
        dElectretComboBox.getEditor().setText(String.valueOf(inputDateModel.getdElectret()));
        epsilonComboBox.getEditor().setText(String.valueOf(inputDateModel.getEpsilon()));
        l2ComboBox.getEditor().setText(String.valueOf(inputDateModel.getL2()));
        l3ComboBox.getEditor().setText(String.valueOf(inputDateModel.getL3()));
        l1ComboBox.getEditor().setText(String.valueOf(inputDateModel.getL1()));
        p_ComboBox.getEditor().setText(String.valueOf(inputDateModel.getP_()));
        UComboBox.getEditor().setText(String.valueOf(inputDateModel.getU()));
        MComboBox.getEditor().setText(String.valueOf(inputDateModel.getM()));
        dCabelComboBox.getEditor().setText(String.valueOf(inputDateModel.getD()));
        LfComboBox.getEditor().setText(String.valueOf(inputDateModel.getLf()));
        neffComboBox.getEditor().setText(String.valueOf(inputDateModel.getNeff()));
        periodComboBox.getEditor().setText(String.valueOf(inputDateModel.getPeriod()));

        startModeling();
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
            dElectretComboBox.getEditor().setText("");
            epsilonComboBox.getEditor().setText("");

            l2ComboBox.getEditor().setText("");
            l3ComboBox.getEditor().setText("");
            l1ComboBox.getEditor().setText("");
            p_ComboBox.getEditor().setText("");
            UComboBox.getEditor().setText("");
            MComboBox.getEditor().setText("");
            dCabelComboBox.getEditor().setText("");
            LfComboBox.getEditor().setText("");
            neffComboBox.getEditor().setText("");
            periodComboBox.getEditor().setText("");
            resultListView.getItems().clear();

            chartOne.getData().clear();
            chartTwo.getData().clear();
            chartFive.getData().clear();
            chartFour.getData().clear();
            chartThree.getData().clear();
        }
    }

    @FXML
    private void createFileDoc() throws Exception
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Создать отчёт");


        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]
                {
                        new FileChooser.ExtensionFilter("Text Files", new String[]{"*.pdf"}),
                        new FileChooser.ExtensionFilter("PDF", new String[]{"*.pdf"})
                });



        File file = fileChooser.showSaveDialog(new Stage());
        //заполнение файла

        WritableImage imageChartOne = chartOne.snapshot(new SnapshotParameters(), null);
        File fileChartOne = new File("chartOne.png");

        WritableImage imageChartTwo = chartTwo.snapshot(new SnapshotParameters(), null);
        File fileChartTwo = new File("chartTwo.png");

        WritableImage imageChartThree = chartThree.snapshot(new SnapshotParameters(), null);
        File fileChartThree = new File("chartThree.png");

        WritableImage imageChartFour = chartFour.snapshot(new SnapshotParameters(), null);
        File fileChartFour = new File("chartFour.png");

        WritableImage imageChartFive = chartFive.snapshot(new SnapshotParameters(), null);
        File fileChartFive = new File("chartFive.png");

        try
        {
            ImageIO.write(SwingFXUtils.fromFXImage(imageChartOne, null), "png", fileChartOne);
            ImageIO.write(SwingFXUtils.fromFXImage(imageChartTwo, null), "png", fileChartTwo);
            ImageIO.write(SwingFXUtils.fromFXImage(imageChartThree, null), "png", fileChartThree);
            ImageIO.write(SwingFXUtils.fromFXImage(imageChartFour, null), "png", fileChartFour);
            ImageIO.write(SwingFXUtils.fromFXImage(imageChartFive, null), "png", fileChartFive);
        }
        catch (IOException e)
        {
            // TODO: handle exception here
        }
        Report.saveReport(dataRoport, file, inputDateModel, "chartOne.png","chartTwo.png","chartThree.png","chartFour.png", "chartFive.png");
        //file.createNewFile();
        System.out.println(file);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Успешно!");
        alert.setHeaderText(null);
        alert.setContentText("Файл "+file.getName()+" – успешно создан!");

        alert.showAndWait();

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
        ProcedureModel.save(file.getPath(), inputDateModel);
        System.out.println(file);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Успешно!");
        alert.setHeaderText(null);
        alert.setContentText("Модель "+file.getName()+" – успешно сохранена!");

        alert.showAndWait();
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

        setParameters(ProcedureModel.load(file.getPath()));


    }



    private void drawGraphicTwo(double U,double p_, double eps, double R, double l1, double l2, double l3, double M)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение удлинение релаксора к E");

        double E = Electret.tension(R,eps,l2/2,l1,l2,l3,p_,U);

        for(double i = 0; i <= E; i+=E/20)
        {
            double delta = Electret.delta_l(l2,M,i);
            series.getData().add(new XYChart.Data(i*Math.pow(10,9),delta*Math.pow(10,24)));
        }

        chartTwo.getYAxis().setLabel("Удлинение релаксора l, им");
        chartTwo.getXAxis().setLabel("Напряженность, E, нВ/м");

        chartTwo.getData().add(series);

        chartTwo.setTitle("Отношение удлинения релаксора к E");
    }

    private void drawGraphicOne(double U,double p_, double eps, double R, double l1, double l2, double l3, double x)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение E к x");


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



    private void drawGraphicThree(double R, double eps, double x, double l1, double l2, double l3, double p_, double U, double M)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение E к u");


        double E = Electret.tension(R,eps,x,l1,l2,l3,p_,U);

        for(double i = 0; i <= E; i+=E/20)
        {
            double u = Electret.deformation(M,i);
            series.getData().add(new XYChart.Data(i*Math.pow(10,9),u*Math.pow(10,12)));
        }

        chartThree.getXAxis().setLabel("Напряженность, E, нВ/м");
        chartThree.getYAxis().setLabel("Деформация, u, 10ˉ¹²");
        chartThree.getData().add(series);

        chartThree.setTitle("Отношение E к u");
    }

    private void drawGraphicFive(double period, double neff, double R, double eps, double x, double l1, double l2, double l3, double p_, double U, double M)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение λ к U");

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

    private void drawGraphicFour(double R, double eps, double x, double l1, double l2, double l3, double p_, double U)
    {
        XYChart.Series series = new XYChart.Series();

        series.setName("Отношение E к U");

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
    public void showLibrary() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/library.fxml"));
        Parent root = loader.load();
        Library library = loader.getController();
       // library.setProgressIndicator(progressIndicator);
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
        Model model = loader.getController();
        model.setModel(inputDateModel);
        //Parent root = FXMLLoader.load(getClass().getResource("../views/model.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("views/image/cirkul.png"));
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
        chartFour.getData().clear();
        chartFive.getData().clear();

        double R = Double.valueOf(dElectretComboBox.getEditor().getText())/2;
        double eps= Double.valueOf(epsilonComboBox.getEditor().getText());
        double x = Double.valueOf(l3ComboBox.getEditor().getText())/2;
        double l2= Double.valueOf(l3ComboBox.getEditor().getText());
        double l3= Double.valueOf(l2ComboBox.getEditor().getText());
        double l1 = Double.valueOf(l1ComboBox.getEditor().getText());
        double p_ =Double.valueOf(p_ComboBox.getEditor().getText());
        double U = Double.valueOf(UComboBox.getEditor().getText());
        double M = Double.valueOf(MComboBox.getEditor().getText());
        double dCabel = Double.valueOf(dCabelComboBox.getEditor().getText());
        double Lf = Double.valueOf(LfComboBox.getEditor().getText());
        double neff = Double.valueOf(neffComboBox.getEditor().getText());
        double period = Double.valueOf(periodComboBox.getEditor().getText());

        double p = Electret.dipoleMoment(R,l3,p_);
        double F = Electret.force(p,l2);
        double Error = Electret.error(l2,R);
        double E = Electret.tension(R,eps,x,l1,l2,l3,p_,U);
        double u = Electret.deformation(M, E);
        double Fmax = Electret.maxForce(E,eps,dCabel);
        double lamda = Electret.lengthWave(neff, period);
        double lamdaRes = Electret.lengthWave(neff, period*u+period);

        System.out.println("res"+lamdaRes);
        System.out.println("lamda"+lamda);

        drawGraphicOne(U,p_,eps,R,l1,l2,l3,l2);
        drawGraphicTwo(U,p_,eps,R,l1,l2,l3,M);
        //drawGraphicThree(l2,R);
        drawGraphicThree(R,eps,x,l1,l2,l3,p_,U,M);
        drawGraphicFour(R,eps,x,l1,l2,l3,p_,U);
        drawGraphicFive(period, neff,  R,  eps,  x,  l1,  l2,  l3,  p_,  U,  M);

        dataRoport = new DataRoport();

        dataRoport.setdCabel(dCabel);
        dataRoport.setDeformation(u);
        dataRoport.setE(E);
        dataRoport.setEps(eps);
        dataRoport.setError(Error);
        dataRoport.setF(F);
        dataRoport.setFmax(Fmax);
        dataRoport.setL1(l1);
        dataRoport.setL2(l2);
        dataRoport.setL3(l3);
        dataRoport.setU(U);
        dataRoport.setR(R);
        dataRoport.setP_(p_);
        dataRoport.setP(p);
        dataRoport.setM(M);
        dataRoport.setLf(Lf);
        dataRoport.setLamda(lamda);
        dataRoport.setLamdaRes(lamdaRes);
        dataRoport.setPeriod(period);
        dataRoport.setNeff(neff);
        dataRoport.setP(p);



        resultListView.getItems().add("Длина волны: "+lamda);
        resultListView.getItems().add("Длина отраженной волны: "+lamdaRes);
        resultListView.getItems().add("Деформация(u): "+u);
        resultListView.getItems().add("Дипольный момент электрета (p): "+p+" Кл*м");
        resultListView.getItems().add("Регистрируемая сила (Fmax): "+Fmax*Math.pow(10,12)+" пН");
        resultListView.getItems().add("Сила взаимодействия электретов (F): "+F+" Н");
        resultListView.getItems().add("Напряженность (E): "+E*Math.pow(10,9)+" нВ/м");
        resultListView.getItems().add("Ошибка: "+Error+" %");



        inputDateModel.setL1(l1);
        inputDateModel.setD(dCabel);
        inputDateModel.setdElectret(R*2);
        inputDateModel.setL2(l2);
        inputDateModel.setLf(Lf);
        inputDateModel.setL3(l3);
        inputDateModel.setEpsilon(eps);
        inputDateModel.setM(M);
        inputDateModel.setNeff(neff);
        inputDateModel.setP_(p_);
        inputDateModel.setPeriod(period);
        inputDateModel.setU(U);///Проверить

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

        dElectretComboBox.setItems(observableList);
    }


    @FXML
    public  void getListDistanceElectrets() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1001);

        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());

        l2ComboBox.setItems(observableList);
    }



    @FXML
    public void getListElectretPenetration() throws  SQLException
    {
        Map mapConstant = procedure.selectRecords(1003);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        epsilonComboBox.setItems(observableList);
    }


    @FXML
    public void getM() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1007);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        MComboBox.setItems(observableList);
    }


    @FXML
    public void getLf() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1006);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        LfComboBox.setItems(observableList);
    }

    @FXML
    public void getP() throws SQLException
    {
        Map mapConstant = procedure.selectRecords(1005);
        ObservableList<Object> observableList = FXCollections.observableArrayList(mapConstant.values().toArray());
        p_ComboBox.setItems(observableList);
    }

    @FXML
    public void showOutputMode() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/outputMode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("views/image/application.png"));
        stage.setTitle("Моделирование датчика по выходным параметрам");
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
    public void showHelp() throws IOException
    {
        File file =new File("help/help.chm");
        Desktop.getDesktop().open(file);
    }
}
