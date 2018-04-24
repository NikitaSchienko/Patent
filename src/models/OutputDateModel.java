package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OutputDateModel
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
}
