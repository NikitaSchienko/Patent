package controllers;

import electret.Electret;
import electret.FormulasOutputMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.ModelOutputMode;

import java.util.ArrayList;
import java.util.Random;

public class ThreadController extends Task
{
    ControllerOutputMode controllerOutputMode;
    Thread thread;

    public ThreadController(ControllerOutputMode controllerOutputMode)
    {
        this.controllerOutputMode = controllerOutputMode;

    }



    @Override
    protected Object call() throws Exception
    {
        Random random = new Random();

        double p_ = Double.valueOf(controllerOutputMode.getpComboBox().getEditor().getText());
        double eps = Double.valueOf(controllerOutputMode.getEpsilonComboBox().getEditor().getText());
        double lamda1 = Double.valueOf(controllerOutputMode.getLamdaComboBox().getEditor().getText());
        double lamda2 = Double.valueOf(controllerOutputMode.getLamdaResultComboBox().getEditor().getText());
        double d = Double.valueOf(controllerOutputMode.getdComboBox().getEditor().getText());
        double M = Double.valueOf(controllerOutputMode.getMComboBox().getEditor().getText());
        double neff = Double.valueOf(controllerOutputMode.getNeffComboBox().getEditor().getText());
        double period = FormulasOutputMode.periodVBR(lamda1,neff);

        ArrayList<ModelOutputMode> arrayList = new ArrayList<ModelOutputMode>();

        double resE = Math.sqrt(FormulasOutputMode.deformation(lamda1,lamda2)/M);

        for(int i = 0; i < 100000; i++)
        {

            double R = (double) (random.nextInt(300) + 100) / 100000;
            double l1 = (double) (random.nextInt(100) + 10) / 100000;
            double l2 = (double) (random.nextInt(300) + 500) / 100000;
            double l3 = (double) (random.nextInt(200) + 50) / 100000;
            double U = (double) (random.nextInt(800) + 100) / 10;

            double E = Electret.tension(R, 0.21, l2/2, l1, l2, l3, 0.000005, U);




            System.out.println("R = " + R);
            System.out.println("l1 = " + l1);
            System.out.println("l2 = " + l2);
            System.out.println("l3 = " + l3);

            //System.out.println("F="+F);
            //System.out.println("l="+l);


            if(Math.abs(E-resE)<0.1)
            {
                System.out.println("E = "+E+" and resE = "+resE);
                double l = FormulasOutputMode.deltaL(M,E,l2);
                double F = FormulasOutputMode.force(eps,E,d);
                double u = FormulasOutputMode.deformation(lamda1,lamda2);
                System.out.println("u1 = "+u+" u2 ="+(M*E*E));
                ModelOutputMode modelOutputMode = new ModelOutputMode(i + 1, l1, l2, l3, R, U, l, E, F, u, new CheckBox());
                arrayList.add(modelOutputMode);
            }

        }

        ObservableList observableList = FXCollections.observableArrayList(arrayList);


        controllerOutputMode.getColumnGraphic().setCellValueFactory(new PropertyValueFactory<ModelOutputMode,CheckBox>("check"));
        controllerOutputMode.getColumnNumber().setCellValueFactory(new PropertyValueFactory<ModelOutputMode,Integer>("number"));
        controllerOutputMode.getColumnE().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("E"));
        controllerOutputMode.getColumnl1().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l1"));
        controllerOutputMode.getColumnl2().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l2"));
        controllerOutputMode.getColumnl3().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l3"));
        controllerOutputMode.getColumnF().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("F"));
        controllerOutputMode.getColumnU().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("U"));
        controllerOutputMode.getColumnR().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("R"));
        controllerOutputMode.getColumnL().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("l"));
        controllerOutputMode.getColumnu().setCellValueFactory(new PropertyValueFactory<ModelOutputMode, Double>("deformation"));

        controllerOutputMode.getTableResult().setItems(observableList);
        return null;
    }
}
