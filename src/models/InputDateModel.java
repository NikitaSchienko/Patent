package models;

import javafx.fxml.FXML;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

@XmlRootElement
public class InputDateModel
{
    private double test;
    private double test2;


    public double getTest()
    {
        return test;
    }

    public void setTest(double test)
    {
        this.test = test;
    }

    public double getTest2()
    {
        return test2;
    }

    public void setTest2(double test2)
    {
        this.test2 = test2;
    }

    @Override
    public String toString()
    {
        return "InputDateModel{" +
                "test=" + test +
                ", test2=" + test2 +
                '}';
    }
}
