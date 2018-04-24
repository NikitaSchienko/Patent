package controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;


public class Model
{
    @FXML
    private Canvas canvas;

    @FXML
    private Slider zoomSlider;

    @FXML
    private AnchorPane pane;

    private double countScroll;
    private double currentX;
    private double currentY;

    private double currentModelX;
    private double currentModelY;




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

        currentModelX = 150;
        currentModelY = 100;
        currentX = 150;
        currentY = 100;
        countScroll = 1;

        zoomSlider.setMin(0.1);
        zoomSlider.setMax(5);
        zoomSlider.setValue(countScroll);

        paintModel(countScroll, currentX, currentY);


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

        Font font=new Font ("TimesRoman", countScroll*14);

        GraphicsContext gc = canvas.getGraphicsContext2D();




        gc.setFont(font);
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setStroke(Color.LIGHTGRAY);
        draw(gc,zoom,x,y);

        gc.setLineWidth(1);

        gc.setStroke(Color.BLACK);
        gc.setFill(Color.GRAY);


        gc.fillRect(100*zoom+x,80*zoom+y,500*zoom,50*zoom);//корпус датчика вверх
        gc.fillRect(100*zoom+x,280*zoom+y,500*zoom,50*zoom);//корпус датчика низ

        gc.strokeRect(100*zoom+x,80*zoom+y,500*zoom,50*zoom);//корпус датчика вверх
        gc.strokeRect(100*zoom+x,280*zoom+y,500*zoom,50*zoom);//корпус датчика низ

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(100*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);//электрет левый
        gc.strokeRect(100*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);

        gc.fillRect(550*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);//электрет правый
        gc.strokeRect(550*zoom+x, 130*zoom+y, 50*zoom, 150*zoom);

        gc.setFill(Color.DARKGRAY);
        gc.fillRect(150*zoom+x,180*zoom+y,400*zoom,50*zoom);//активный диэлектрик
        gc.strokeRect(150*zoom+x,180*zoom+y,400*zoom,50*zoom);

        gc.setFill(Color.GRAY);
        gc.fillRect(50*zoom+x,400*zoom+y,850*zoom,30*zoom);
        gc.strokeLine(50*zoom+x,400*zoom+y, 900*zoom+x,400*zoom+y);//кабель

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(50*zoom+x,430*zoom+y,850*zoom,50*zoom);
        gc.strokeLine(50*zoom+x,430*zoom+y, 900*zoom+x,430*zoom+y);

        gc.setFill(Color.GRAY);
        gc.fillRect(50*zoom+x,480*zoom+y,850*zoom,30*zoom);
        gc.strokeLine(50*zoom+x,480*zoom+y, 900*zoom+x,480*zoom+y);
        gc.strokeLine(50*zoom+x,510*zoom+y, 900*zoom+x,510*zoom+y);


        gc.strokeRect(300*zoom+x,430*zoom+y, 100*zoom,50*zoom);//первая решетка ВБР
        gc.strokeRect(310*zoom+x,430*zoom+y, 80*zoom,50*zoom);
        gc.strokeRect(320*zoom+x,430*zoom+y, 60*zoom,50*zoom);
        gc.strokeRect(330*zoom+x,430*zoom+y, 40*zoom,50*zoom);
        gc.strokeRect(340*zoom+x,430*zoom+y, 20*zoom,50*zoom);
        gc.strokeRect(350*zoom+x,430*zoom+y, 10*zoom,50*zoom);


        gc.strokeRect(700*zoom+x,430*zoom+y, 100*zoom,50*zoom);//вторая пешетка ВБР
        gc.strokeRect(710*zoom+x,430*zoom+y, 80*zoom,50*zoom);
        gc.strokeRect(720*zoom+x,430*zoom+y, 60*zoom,50*zoom);
        gc.strokeRect(730*zoom+x,430*zoom+y, 40*zoom,50*zoom);
        gc.strokeRect(740*zoom+x,430*zoom+y, 20*zoom,50*zoom);
        gc.strokeRect(750*zoom+x,430*zoom+y, 10*zoom,50*zoom);


        gc.setFill(Color.LIGHTGRAY);//держатели датчика
        gc.fillRect(90*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);
        gc.strokeRect(90*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);

        gc.fillRect(600*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);
        gc.strokeRect(600*zoom+x, 80*zoom+y, 10*zoom, 430*zoom);

        //длина активного диэлектрика
        gc.strokeLine(150*zoom+x,30*zoom+y,150*zoom+x,230*zoom+y);
        gc.strokeLine(550*zoom+x,30*zoom+y,550*zoom+x,230*zoom+y);
        gc.strokeLine(150*zoom+x,40*zoom+y,550*zoom+x,40*zoom+y);
        gc.strokeText("???", 340*zoom+x, 30*zoom+y);


        //длина ВБР
        gc.strokeLine(300*zoom+x,430*zoom+y, 300*zoom+x,550*zoom+y);
        gc.strokeLine(400*zoom+x,430*zoom+y, 400*zoom+x,550*zoom+y);
        gc.strokeLine(300*zoom+x,540*zoom+y, 400*zoom+x,540*zoom+y);
        gc.strokeText("???", 340*zoom+x, 560*zoom+y);

        //высота электрета
        gc.strokeLine(550*zoom+x, 130*zoom+y, 650*zoom+x, 130*zoom+y);
        gc.strokeLine(550*zoom+x, 280*zoom+y, 650*zoom+x, 280*zoom+y);
        gc.strokeLine(640*zoom+x,130*zoom+y, 640*zoom+x,280*zoom+y);
        gc.strokeText("???", 650*zoom+x, 210*zoom+y);

        //длина активного диэлектрика и электретов
        gc.strokeLine(100*zoom+x,zoom+y,100*zoom+x,230*zoom+y);
        gc.strokeLine(600*zoom+x,zoom+y,600*zoom+x,230*zoom+y);
        gc.strokeLine(100*zoom+x,10*zoom+y,600*zoom+x,10*zoom+y);
        gc.strokeText("???", 340*zoom+x, zoom+y);

        //высота активного диэлектрика
        gc.strokeLine(50*zoom+x, 180*zoom+y, 150*zoom+x, 180*zoom+y);
        gc.strokeLine(50*zoom+x, 230*zoom+y, 150*zoom+x, 230*zoom+y);
        gc.strokeLine(60*zoom+x,180*zoom+y, 60*zoom+x,230*zoom+y);
        gc.strokeText("???", 20*zoom+x, 210*zoom+y);

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

