package electret;

public class Electret
{
    private static final double DIELECTRIC_CONSTANT = 0.00000000000885418782;
    private static final double PI = 3.14;

    private static final double T = PI;

//    private double defference;
//    private double distance;
//    private double radiusToDiametr;


//    public Electret(double difference, double distance, double radiusToDiametr)
//    {
//        this.defference = difference;
//        this.distance = distance;
//        this.radiusToDiametr = radiusToDiametr;
//    }

    public static Double lengthWave(double neff, double period)
    {
        return 2*neff*period;
    }

    public static Double deformation(double M, double E)
    {
        return M*Math.pow(E,2);
    }

    public static Double force(double p, double l2)
    {
        return (6*p*p)/(4*PI*DIELECTRIC_CONSTANT*l2);
    }

    public static Double maxForce(double E, double eps, double dCabel)
    {
        return E*eps*Math.PI*Math.pow(dCabel,2)/4;
    }

    //Напряженность
    public static Double delta_l(double l2, double M, double E)
    {
        return M*l2*E*E;
    }

    public static Double U(double q,double l1, double l2, double l3, double R, double p_, double eps)
    {
        Double E1 = 1/(2*DIELECTRIC_CONSTANT)*(
                q*(
                        childE1(0,l1,R) +childE2(0,l1,R, l1,l2,l3))+
                p_*(
                        childE3(0,l1,R,l1)
                        -childE4(0,l1,R,l1,l2)
                        +childE5(0,l1,R,l1,l2,l3)
                        -childE6(0,l1,R,l1,l2,l3)
                )
        );
        Double E2 = 1/(2*DIELECTRIC_CONSTANT)*(
                q*(
                        childE1(l1,l1+l2,R) +childE2(l1,l1+l2,R, l1,l2,l3))+
                        p_*(
                                -childE3(l1,l1+l2,R,l1)
                                -childE4(l1,l1+l2,R,l1,l2)
                                +childE5(l1,l1+l2,R,l1,l2,l3)
                                -childE6(l1,l1+l2,R,l1,l2,l3)
                        )
        );
        Double E3 = 1/(2*DIELECTRIC_CONSTANT)*eps*(
                q*(
                        childE1(l1+l2,l1+l2+l3,R) +childE2(l1+l2,l1+l2+l3,R, l1,l2,l3))+
                        p_*(
                                -childE3(l1+l2,l1+l2+l3,R,l1)
                                +childE4(l1+l2,l1+l2+l3,R,l1,l2)
                                +childE5(l1+l2,l1+l2+l3,R,l1,l2,l3)
                                -childE6(l1+l2,l1+l2+l3,R,l1,l2,l3)
                        )
        );

        Double E4 = 1/(2*DIELECTRIC_CONSTANT)*(
                q*(
                        childE1(l1+l2+l3,l1+2*l2+l3,R) +childE2(l1+l2+l3,l1+2*l2+l3,R, l1,l2,l3))+
                        p_*(
                                -childE3(l1+l2+l3,l1+2*l2+l3,R,l1)
                                +childE4(l1+l2+l3,l1+2*l2+l3,R,l1,l2)
                                -childE5(l1+l2+l3,l1+2*l2+l3,R,l1,l2,l3)
                                -childE6(l1+l2+l3,l1+2*l2+l3,R,l1,l2,l3)
                        )
        );

        Double E5 = 1/(2*DIELECTRIC_CONSTANT)*(
                q*(
                        childE1(l1+2*l2+l3,2*l1+2*l2+l3,R) +childE2(l1+l2+l3,2*l1+2*l2+l3,R, l1,l2,l3))+
                        p_*(
                                 childE3(l1+2*l2+l3,2*l1+2*l2+l3,R,l1)
                                -childE4(l1+2*l2+l3,2*l1+2*l2+l3,R,l1,l2)
                                +childE5(l1+2*l2+l3,2*l1+2*l2+l3,R,l1,l2,l3)
                                -childE6(l1+2*l2+l3,2*l1+2*l2+l3,R,l1,l2,l3)
                        )
        );

        return E1+E2+E3+E4+E5;
    }

    private static Double childE1(double a, double b, double R)
    {
        Double resultA = Math.sqrt(R*R+a*a);
        Double resultB = Math.sqrt(R*R+b*b);

        return resultB - resultA;
    }

    private static Double childE2(double a, double b, double R, double l1, double l2, double l3)
    {
        Double resultA = Math.sqrt((2*l1+2*l2*l3-a)*(2*l1+2*l2*l3-a)+R*R) + a;
        Double resultB = Math.sqrt((2*l1+2*l2*l3-b)*(2*l1+2*l2*l3-b)+R*R) + b;

        return resultB - resultA;
    }

    private static Double childE3(double a, double b, double R, double l1)
    {
        Double resultA = Math.sqrt(l1*l1-2*l1*a+R*R+a*a) + a;
        Double resultB = Math.sqrt(l1*l1-2*l1*b+R*R+b*b) + b;

        return resultB - resultA;
    }

    private static Double childE4(double a, double b, double R, double l1, double l2)
    {
        Double resultA = Math.sqrt((l1+l2-a)*(l1+l2-a)+R*R) + a;
        Double resultB = Math.sqrt((l1+l2-b)*(l1+l2-b)+R*R) + b;

        return resultB - resultA;
    }

    private static Double childE5(double a, double b, double R, double l1, double l2, double l3)
    {
        Double resultA = Math.sqrt((l1+l2+l3-a)*(l1+l2+l3-a)+R*R) + a;
        Double resultB = Math.sqrt((l1+l2+l3-b)*(l1+l2+l3-b)+R*R) + b;

        return resultB - resultA;
    }

    private static Double childE6(double a, double b, double R, double l1, double l2, double l3)
    {
        Double resultA = Math.sqrt((l1+2*l2+l3-a)*(l1+2*l2+l3-a)+R*R) + a;
        Double resultB = Math.sqrt((l1+2*l2+l3-b)*(l1+2*l2+l3-b)+R*R) + b;

        return resultB - resultA;
    }




    public static Double tensionInPoint(double charge, double E, double R, double d, double x)
    {
        return charge/(2*PI*E*DIELECTRIC_CONSTANT)*((1-(x/(Math.sqrt(R*R+x*x))))+(1-((d-x)/Math.sqrt(R*R+(d-x)*(d-x)))));
    }

    public static Double tension(double R, double eps, double x, double l1, double l2, double l3, double p_, double U)
    {
        double var1 = 1 - x / (Math.sqrt(R * R + x * x));
        double var2 = 1 - (2 * l1 + 2 * l2 + l3 - x) / (Math.sqrt(R * R + Math.pow((2 * l1 + 2 * l2 + l3 - x), 2)));
        double var3 = 1 - (x - l1) / Math.sqrt(R * R + (x-l1)*(x-l1));
        double var4 = 1 - (x - l1 - l2) / Math.sqrt(R * R + Math.pow((x - l1 - l2), 2));
        double var5 = 1 - (l1 + l2 + l3 - x) / Math.sqrt(R * R + Math.pow((l1 + l2 + l3 - x), 2));
        double var6 = 1 - (l1 + 2 * l2 + l3 - x) / Math.sqrt(R * R + Math.pow((l1 + 2 * l2 + l3 - x), 2));

        double q = Electret.q(U,l1,l2,l3,R,p_,eps);
        //System.out.println(q);
        //double E = tensionInPoint(q,eps,R,l2,x);
        double E =  1/(2*DIELECTRIC_CONSTANT*eps)*(q*(var1+var2)+p_*(-var3+var4+var5-var6));
        return E;
//        return (q,l1, l2, l3, R, U, p_, E);

    }



    public static Double q(double U, double l1,double l2, double l3, double R,double p_, double E)
    {
//        System.out.println("-------");
//        System.out.println("U = "+U);
//        System.out.println("B = "+B(l1,l2,l3,R,p_,E));
//        System.out.println("A = "+A(l1,l2,l3,R,E));
//        System.out.println("-------");
        double var = (U-B(l1,l2,l3,R,p_,E))/A(l1,l2,l3,R,E);
        return var;
    }

    private static Double childA(double a, double b, double l1,double l2, double l3, double R)
    {
        Double resultA = Math.sqrt((2*l1+2*l2+l3-a)*(2*l1+2*l2+l3-a))-Math.sqrt(R*R+a*a)+2*a;
        Double resultB = Math.sqrt((2*l1+2*l2+l3-b)*(2*l1+2*l2+l3-b))-Math.sqrt(R*R+b*b)+2*b;

        return 1/(2*DIELECTRIC_CONSTANT)*(resultB - resultA);
    }

    private static Double A(double l1,double l2, double l3, double R, double E)
    {
        Double A1 = 1/(2*DIELECTRIC_CONSTANT)*childA(0,l1,l1,l2,l3,R);
        Double A2 = 1/(2*DIELECTRIC_CONSTANT)*childA(l1,l1+l2,l1,l2,l3,R);
        Double A3 = 1/(2*DIELECTRIC_CONSTANT*E)*childA(l1+l2,l1+l2+l3,l1,l2,l3,R);
        Double A4 = 1/(2*DIELECTRIC_CONSTANT)*childA(l1+l2+l3,l1+2*l2+l3,l1,l2,l3,R);
        Double A5 = 1/(2*DIELECTRIC_CONSTANT)*childA(l1+2*l2+l3,2*l1+2*l2+l3,l1,l2,l3,R);

        return A1+A2+A3+A4+A5;
    }

    public static Double B(double l1,double l2, double l3, double R, double p_, double E)
    {
        Double B1 = p_/(2*DIELECTRIC_CONSTANT)*(childB1(0,l1,l1,R)-childB2(0,l1,l1,l2,R)+childB3(0,l1,l1,l2,l3,R)-childB4(0,l1,l1,l2,l3,R));
        Double B2 = p_/(2*DIELECTRIC_CONSTANT)*(-childB1(l1,l1+l2,l1,R)-childB2(l1,l1+l2,l1,l2,R)+childB3(l1,l1+l2,l1,l2,l3,R)-childB4(l1,l1+l2,l1,l2,l3,R));
        Double B3 = p_/(2*DIELECTRIC_CONSTANT*E)*(-childB1(l1,l1+l2,l1,R)+childB2(l1,l1+l2,l1,l2,R)+childB3(l1,l1+l2,l1,l2,l3,R)-childB4(l1,l1+l2,l1,l2,l3,R));
        Double B4 = p_/(2*DIELECTRIC_CONSTANT)*(-childB1(l1,l1+l2,l1,R)+childB2(l1,l1+l2,l1,l2,R)-childB3(l1,l1+l2,l1,l2,l3,R)-childB4(l1,l1+l2,l1,l2,l3,R));
        Double B5 = B5(l1+2*l2+l3,2*l1+2*l2+l3,l1,l2,l3, R,p_);
        return B1+B2+B3+B4+B5;
    }

    public static Double childB1(double a, double b, double l1,double R)
    {
        Double resultA = Math.sqrt(l1*l1-2*l1*a+R*R+a*a)+a;
        Double resultB = Math.sqrt(l1*l1-2*l1*b+R*R+b*b)+b;
        return (resultB - resultA);
    }

    public static Double childB2(double a, double b, double l1,double l2, double R)
    {
        Double resultA = Math.sqrt((l1+l2-a)*(l1+l2-a)+R*R)+a;
        Double resultB = Math.sqrt((l1+l2-b)*(l1+l2-b)+R*R)+b;
        return (resultB - resultA);
    }
    public static Double childB3(double a, double b, double l1,double l2, double l3, double R)
    {
        Double resultA = Math.sqrt((l1+l2+l3-a)*(l1+l2+l3-a)+R*R)+a;
        Double resultB = Math.sqrt((l1+l2+l3-b)*(l1+l2+l3-b)+R*R)+b;
        return (resultB - resultA);
    }
    public static Double childB4(double a, double b, double l1,double l2, double l3, double R)
    {
        Double resultA = Math.sqrt((l1+2*l2+l3-a)*(l1+2*l2+l3-a)+R*R)+a;
        Double resultB = Math.sqrt((l1+2*l2+l3-b)*(l1+2*l2+l3-b)+R*R)+b;
        return (resultB - resultA);
    }


    public static Double B5(double a, double b, double l1,double l2, double l3, double R, double p_)
    {
        Double resultA = Math.sqrt(l1*l1-2*l1*a+R*R+a)-Math.sqrt((l1+l2-a)*(l1+l2-a)+R*R) + Math.sqrt((l1+l2+l3-a)*(l1+l2+l3-a)+R*R)-Math.sqrt((l1+2*l2-l3-a)*(l1+2*l2-l3-a)+R*R);
        Double resultB = Math.sqrt(l1*l1-2*l1*b+R*R+b)-Math.sqrt((l1+l2-b)*(l1+l2-b)+R*R) + Math.sqrt((l1+l2+l3-b)*(l1+l2+l3-b)+R*R)-Math.sqrt((l1+2*l2-l3-b)*(l1+2*l2-l3-b)+R*R);
        return p_/(2*DIELECTRIC_CONSTANT)*(resultB-resultA);
    }


    public static Double deltaLambdaB(double pe, double lambdaB, double bz)
    {
        return lambdaB*(1-pe)*bz;
    }

    public static Double lambdaB(double neff,  double period)
    {
        return 2*neff*period;
    }

    //Дипольный момент электрета
    public static Double dipoleMoment(double R, double l3, double p_)
    {
        return T*Math.pow(R,2)*l3*p_;
    }


    public static Double error(double l2, double R)
    {
        return l2*Math.sqrt(1/(Math.pow(R,2)+Math.pow(l2,2)));
    }
//
//    public Double difference()
//    {
//        return 1/Math.sqrt(1+Math.pow(radiusToDiametr,2));
//    }

}
