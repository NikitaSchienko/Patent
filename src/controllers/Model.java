package controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import models.InputDateModel;


public class Model
{
    @FXML
    private Canvas canvas;

    @FXML
    private Slider zoomSlider;

    @FXML
    private AnchorPane pane;

    @FXML
    private ListView componentsOfPatentList;


    private double countScroll;
    private double currentX;
    private double currentY;

    private double currentModelX;
    private double currentModelY;

    private InputDateModel inputDateModel;

    public void setModel(InputDateModel inputDateModel)
    {
        this.inputDateModel = inputDateModel;
        paintModel(countScroll, currentX, currentY);
    }

    @FXML
    public void scroll(ScrollEvent event)
    {

            if (event.getDeltaY() > 0 && countScroll < 5 )
            {
                countScroll += 0.1;
            }
            else if(countScroll > 0.2 && event.getDeltaY() < 0)
            {
                countScroll -= 0.1;
            }
        zoomSlider.setValue(countScroll);
        paintModel(countScroll, currentModelX, currentModelY);
    }

    @FXML
    private  void onMouseDraggedSlider()
    {
        countScroll = zoomSlider.getValue();
        paintModel(countScroll,currentModelX,currentModelY);
        //System.out.println();
    }

    @FXML
    public void initialize()
    {

        components();

        currentModelX = 150;
        currentModelY = 100;
        currentX = 150;
        currentY = 100;
        countScroll = 3;

        zoomSlider.setMin(0.1);
        zoomSlider.setMax(5);
        zoomSlider.setValue(countScroll);




    }


    private void components()
    {
        componentsOfPatentList.getItems().add("1 - Внутренний цилиндрический\nкорпус датчика");
        componentsOfPatentList.getItems().add("2 - Оптический вход");
        componentsOfPatentList.getItems().add("3 - Оптический выход");
        componentsOfPatentList.getItems().add("4 - ВБР 1");
        componentsOfPatentList.getItems().add("5 - ВБР 2");
        componentsOfPatentList.getItems().add("6(2) - Электреты");
        componentsOfPatentList.getItems().add("7 - Одномодовый оптоволоконный\nкабель");
        componentsOfPatentList.getItems().add("8 - Активный диэлектрик");
        componentsOfPatentList.getItems().add("9(2) - Электроды");
        componentsOfPatentList.getItems().add("10(2) - Контакты");
        componentsOfPatentList.getItems().add("11 - Cъемная часть внешнего корпуса");
        componentsOfPatentList.getItems().add("12 - Венний корпус датчика");


    }

    private void draw(GraphicsContext gc, double zoom, double x, double y)
    {

        gc.setLineWidth(0.5);

        for (int i = 0; i < canvas.getHeight(); i+=10)
        {
            gc.strokeLine(0,i, canvas.getWidth(),i);//кабель
        }

        for (int i = 0; i < canvas.getWidth(); i+=10)
        {
            gc.strokeLine(i,0, i,canvas.getHeight());//кабель
        }

    }

    private void paintModel(double zoom, double x, double y)
    {

        Font font=new Font ("TimesRoman", countScroll*8);

        GraphicsContext gc = canvas.getGraphicsContext2D();


        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setStroke(Color.BLACK);
//        draw(gc,zoom,x,y);


        Image image = new Image("views/image/patent.png");

        gc.drawImage(image,zoom+x,zoom+y,image.getWidth()*0.2*zoom, image.getHeight()*0.2*zoom);

        gc.setLineWidth(1);

        gc.setFill(Color.BLUE);
//
//
//        gc.fillRect(100*zoom+x,80*zoom+y,500*zoom,50*zoom);//корпус датчика вверх
//        gc.fillRect(100*zoom+x,280*zoom+y,500*zoom,50*zoom);//корпус датчика низ
//
//        gc.strokeRect(100*zoom+x,80*zoom+y,500*zoom,50*zoom);//корпус датчика вверх
//        gc.strokeRect(100*zoom+x,280*zoom+y,500*zoom,50*zoom);//корпус датчика низ
//
//        gc.setFill(Color.LIGHTGRAY);
//        gc.fillRect(100*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);//электрет левый
//        gc.strokeRect(100*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);
//
//        gc.fillRect(550*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);//электрет правый
//        gc.strokeRect(550*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);
//
//        gc.setFill(Color.DARKGRAY);
//        gc.fillRect(150*zoom+x,180*zoom+y,400*zoom,50*zoom);//активный диэлектрик
//        gc.strokeRect(150*zoom+x,180*zoom+y,400*zoom,50*zoom);
//
//        gc.setFill(Color.GRAY);
//        gc.fillRect(50*zoom+x,400*zoom+y,850*zoom,30*zoom);
//        gc.strokeLine(50*zoom+x,400*zoom+y, 900*zoom+x,400*zoom+y);//кабель
//
//        gc.setFill(Color.LIGHTGRAY);
//        gc.fillRect(50*zoom+x,430*zoom+y,850*zoom,50*zoom);
//        gc.strokeLine(50*zoom+x,430*zoom+y, 900*zoom+x,430*zoom+y);
//
//        gc.setFill(Color.GRAY);
//        gc.fillRect(50*zoom+x,480*zoom+y,850*zoom,30*zoom);
//        gc.strokeLine(50*zoom+x,480*zoom+y, 900*zoom+x,480*zoom+y);
//        gc.strokeLine(50*zoom+x,510*zoom+y, 900*zoom+x,510*zoom+y);
//
//
//        gc.strokeRect(300*zoom+x,430*zoom+y, 100*zoom,50*zoom);//первая решетка ВБР
//        gc.strokeRect(310*zoom+x,430*zoom+y, 80*zoom,50*zoom);
//        gc.strokeRect(320*zoom+x,430*zoom+y, 60*zoom,50*zoom);
//        gc.strokeRect(330*zoom+x,430*zoom+y, 40*zoom,50*zoom);
//        gc.strokeRect(340*zoom+x,430*zoom+y, 20*zoom,50*zoom);
//        gc.strokeRect(350*zoom+x,430*zoom+y, 10*zoom,50*zoom);
//
//
//        gc.strokeRect(700*zoom+x,430*zoom+y, 100*zoom,50*zoom);//вторая пешетка ВБР
//        gc.strokeRect(710*zoom+x,430*zoom+y, 80*zoom,50*zoom);
//        gc.strokeRect(720*zoom+x,430*zoom+y, 60*zoom,50*zoom);
//        gc.strokeRect(730*zoom+x,430*zoom+y, 40*zoom,50*zoom);
//        gc.strokeRect(740*zoom+x,430*zoom+y, 20*zoom,50*zoom);
//        gc.strokeRect(750*zoom+x,430*zoom+y, 10*zoom,50*zoom);
//
//
//        gc.setFill(Color.LIGHTGRAY);//держатели датчика
//        gc.fillRect(90*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);
//        gc.strokeRect(90*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);
//
//        gc.fillRect(600*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);
//        gc.strokeRect(600*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);
//
//        //длина активного диэлектрика
//        gc.strokeLine(150*zoom+x,30*zoom+y,150*zoom+x,230*zoom+y);
//        gc.strokeLine(550*zoom+x,30*zoom+y,550*zoom+x,230*zoom+y);
//        gc.strokeLine(150*zoom+x,40*zoom+y,550*zoom+x,40*zoom+y);
//        gc.strokeText("???", 340*zoom+x, 30*zoom+y);
//
//
        //
        gc.strokeLine(158*zoom+x,15*zoom+y, 158*zoom+x,100*zoom+y);
        gc.strokeLine(163*zoom+x,1*zoom+y, 163*zoom+x,100*zoom+y);
        gc.strokeLine(172.5*zoom+x,1*zoom+y, 172.5*zoom+x,100*zoom+y);
        gc.strokeLine(224.5*zoom+x,15*zoom+y, 224.5*zoom+x,100*zoom+y);

        gc.strokeLine(130*zoom+x,10*zoom+y, 172.5*zoom+x,10*zoom+y);
        gc.strokeLine(130*zoom+x,20*zoom+y, 163*zoom+x,20*zoom+y);
        gc.strokeLine(172.5*zoom+x,20*zoom+y, 224.5*zoom+x,20*zoom+y);



        //ширина электрета
        double size = inputDateModel.getL3()*1000;
        gc.strokeText(String.valueOf(size), 135*zoom+x, 9*zoom+y);
        //расстояние
        size = inputDateModel.getL2()*1000;
        gc.strokeText(String.valueOf(size), 190*zoom+x, 18*zoom+y);
        //ширина электрода
        size = inputDateModel.getL1()*1000;
        gc.strokeText(String.valueOf(size), 135*zoom+x, 18*zoom+y);

        //Диаметр
        gc.strokeLine(225*zoom+x,61*zoom+y, 300*zoom+x,61*zoom+y);
        gc.strokeLine(225*zoom+x,136.5*zoom+y, 300*zoom+x,136.5*zoom+y);
        gc.strokeLine(280*zoom+x,61*zoom+y, 280*zoom+x,136.5*zoom+y);

        size = inputDateModel.getdElectret()*1000;
        gc.strokeText(String.valueOf(size), 290*zoom+x, 100*zoom+y);

        //Lf
        gc.strokeLine(158*zoom+x,130*zoom+y, 158*zoom+x,210*zoom+y);
        gc.strokeLine(239*zoom+x,130*zoom+y, 239*zoom+x,210*zoom+y);
        gc.strokeLine(158*zoom+x,202*zoom+y, 239*zoom+x,202*zoom+y);

        size = inputDateModel.getLf()*1000;
        gc.strokeText(String.valueOf(size), 190*zoom+x, 210*zoom+y);

        //D
        gc.strokeLine(300*zoom+x,159.5*zoom+y, 360*zoom+x,159.5*zoom+y);
        gc.strokeLine(300*zoom+x,169*zoom+y, 360*zoom+x,169*zoom+y);

        gc.strokeLine(355*zoom+x,159.5*zoom+y, 355*zoom+x,169*zoom+y);
        size = inputDateModel.getD()*1000;
        gc.strokeText(String.valueOf(size), 362*zoom+x, 167*zoom+y);
        //gc.strokeText("???", 130*zoom+x, 9*zoom+y);
//
//        //высота электрета
//        gc.strokeLine(550*zoom+x, 130*zoom+y, 650*zoom+x, 130*zoom+y);
//        gc.strokeLine(550*zoom+x, 280*zoom+y, 650*zoom+x, 280*zoom+y);
//        gc.strokeLine(640*zoom+x,130*zoom+y, 640*zoom+x,280*zoom+y);
//        gc.strokeText("???", 650*zoom+x, 210*zoom+y);
//
//        //длина активного диэлектрика и электретов
//        gc.strokeLine(100*zoom+x,zoom+y,100*zoom+x,230*zoom+y);
//        gc.strokeLine(600*zoom+x,zoom+y,600*zoom+x,230*zoom+y);
//        gc.strokeLine(100*zoom+x,10*zoom+y,600*zoom+x,10*zoom+y);
//        gc.strokeText("???", 340*zoom+x, zoom+y);
//
//        //высота активного диэлектрика
//        gc.strokeLine(50*zoom+x, 180*zoom+y, 150*zoom+x, 180*zoom+y);
//        gc.strokeLine(50*zoom+x, 230*zoom+y, 150*zoom+x, 230*zoom+y);
//        gc.strokeLine(60*zoom+x,180*zoom+y, 60*zoom+x,230*zoom+y);
//        gc.strokeText("???", 20*zoom+x, 210*zoom+y);

    }


    @FXML
    private void onMouseReleased(MouseEvent mouseEvent)
    {

        //currentX = mouseEvent.getX();
        //currentY = mouseEvent.getY();

        currentModelX += mouseEvent.getX() - currentX;
        currentModelY += mouseEvent.getY() - currentY;

    }



    @FXML
    private void onMouseDragged(MouseEvent mouseEvent)
    {
        double differedX = mouseEvent.getX()- currentX;
        double differedY = mouseEvent.getY()- currentY;


        paintModel(countScroll,differedX+currentModelX,differedY+currentModelY);

    }

    @FXML
    private void onMousePressed(MouseEvent mouseEvent)
    {
        //System.out.println("dragged");
        currentX = mouseEvent.getX();
        currentY = mouseEvent.getY();

        ///System.out.println("pressed "+currentY+"-"+currentX);
    }

    @FXML
    private void onDragDroped(MouseEvent event)
    {

       //System.out.println("droped ");
    }

    @FXML
    private void onDragDetected(MouseEvent event)
    {

        //System.out.println("detected ");
    }

    @FXML
    private void onMouseDragEntered(MouseEvent event)
    {

        System.out.println("sdfg ");
    }

    @FXML
    private void onMouseDragExited()
    {

    }

    @FXML
    private void onMouseDragOver(MouseDragEvent mouseDragEvent)
    {
        System.out.println(mouseDragEvent.getX());
    }

    @FXML
    private void onDragDone(MouseEvent event)
    {

        System.out.println("done ");
    }

    @FXML
    private void  onDragDetected()
    {
        System.out.println("onMouseClicked ");
    }

}

