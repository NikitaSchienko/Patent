package controllers;


import electret.Electret;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class Controller
{
    @FXML
    private Button startModelingButton;

    @FXML
    private ListView<String> resultListView;

    @FXML
    private TextField distanceElectret;

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
    private NumberAxis xAxis = new NumberAxis("Number saved", 1, 10.1, 1);

    @FXML
    private NumberAxis yAxis = new NumberAxis("Calculated Value", 0, 100, 1);


    @FXML
    public void startModeling()
    {
        resultListView.getItems().clear();
        ObservableList<XYChart.Series<Double,Double>> lineChartData = FXCollections.observableArrayList(
                new LineChart.Series<Double,Double>("Series 1", FXCollections.observableArrayList(
                        new XYChart.Data<Double,Double>(0.0, 1.0),
                        new XYChart.Data<Double,Double>(1.2, 1.4),
                        new XYChart.Data<Double,Double>(2.2, 1.9),
                        new XYChart.Data<Double,Double>(2.7, 2.3),
                        new XYChart.Data<Double,Double>(2.9, 0.5)
                )),
                new LineChart.Series<Double,Double>("Series 2", FXCollections.observableArrayList(
                        new XYChart.Data<Double,Double>(0.0, 1.6),
                        new XYChart.Data<Double,Double>(0.8, 0.4),
                        new XYChart.Data<Double,Double>(1.4, 2.9),
                        new XYChart.Data<Double,Double>(2.1, 1.3),
                        new XYChart.Data<Double,Double>(2.6, 0.9)
                ))
        );
        chartOne = new LineChart(xAxis, yAxis, lineChartData);

        //  chart.setAnimated(true);
        //  chart.animatedProperty();
        System.out.println("kaw");

//        if(checkParameters())
//        {

//            Electret electret = new Electret(Double.valueOf(fieldDifference.getText()),
//                    Double.valueOf(distanceElectrets.getText()),
//                    Double.valueOf(radiusToDiametr.getText()));

//            resultListView.getItems().add("R = " + electret.radius() + " м");
//            resultListView.getItems().add("Delta = " + electret.difference() * 100 + "%");
//        }
//        else
//        {
//            resultListView.getItems().add("Некорректные параметры");
//        }
    }



//    private boolean checkParameters()
//    {
//
//        distanceElectrets.setStyle("-fx-text-box-border: silver;");
//        radiusToDiametr.setStyle("-fx-text-box-border: silver;");
//        fieldDifference.setStyle("-fx-text-box-border: silver;");
//
//        boolean check = true;
//        try
//        {
//            if (Double.valueOf(fieldDifference.getText()) <= 0 || Double.valueOf(fieldDifference.getText()) > 1) {
//                fieldDifference.setStyle("-fx-text-box-border: red ;\n" +
//                        "  -fx-focus-color: red ;");
//                check = false;
//            }
//        }
//        catch (Exception e)
//        {
//            fieldDifference.setStyle("-fx-text-box-border: red ;\n" +
//                    "  -fx-focus-color: red ;");
//            check = false;
//        }
//        try
//        {
//            if(Double.valueOf(distanceElectrets.getText()) <= 0)
//            {
//                distanceElectrets.setStyle("-fx-text-box-border: red ;\n" +
//                        "  -fx-focus-color: red ;");
//                check = false;
//            }
//        }
//        catch (Exception e)
//        {
//            distanceElectrets.setStyle("-fx-text-box-border: red ;\n" +
//                    "  -fx-focus-color: red ;");
//            check = false;
//        }
//
//        try
//        {
//            if (Double.valueOf(radiusToDiametr.getText()) <= 0) {
//                radiusToDiametr.setStyle("-fx-text-box-border: red ;\n" +
//                        "  -fx-focus-color: red ;");
//                check = false;
//            }
//
//        }
//        catch (Exception e)
//        {
//            radiusToDiametr.setStyle("-fx-text-box-border: red ;\n" +
//                    "  -fx-focus-color: red ;");
//            check = false;
//        }
//        return check;
//    }

}
