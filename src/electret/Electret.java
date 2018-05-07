package electret;

public class Electret
{
    private static final double DIELECTRIC_CONSTANT = 0.00000000000885418782;
    private static final double PI = 3.14;

//    private double defference;
//    private double distance;
//    private double radiusToDiametr;


//    public Electret(double difference, double distance, double radiusToDiametr)
//    {
//        this.defference = difference;
//        this.distance = distance;
//        this.radiusToDiametr = radiusToDiametr;
//    }


    public static Double force(double p, double e0, double r)
    {
        return (6*p*p)/(4*PI*e0*Math.pow(r,4));
    }

    //Напряженность
    public static Double tensions(double l, double M, double delta_l)
    {
        return Math.sqrt(delta_l/(M*l));
    }


    public static Double tensionInPoint(double charge, double E, double R, double d, double x)
    {
        return charge/(2*PI*E*DIELECTRIC_CONSTANT)*((1-(x/(Math.sqrt(R*R+x*x))))+(1-((d-x)/Math.sqrt(R*R+(d-x)*(d-x)))));
    }


//    public Double radius()
//    {
//        return distance*Math.sqrt(1/Math.pow(defference,2)-1);
//    }
//
//    public Double difference()
//    {
//        return 1/Math.sqrt(1+Math.pow(radiusToDiametr,2));
//    }

}
