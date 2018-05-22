package electret;

public class FormulasOutputMode
{

    private static final double DIELECTRIC_CONSTANT = 0.00000000000885418782;



    public static Double deformation(double lamda1, double lamda2)
    {
        return Math.abs(lamda2-lamda1)/lamda1;
    }

    public static Double deltaL(double M, double E, double l2)
    {
        return Math.sqrt(l2*M*E*E);
    }


    public static Double force(double eps, double E, double d)
    {
        return eps*E*Math.PI*d*d/4;
    }

    public static Double forceMax()
    {
        return null;
    }

    public static Double periodVBR(double lambda, double neff)
    {
        return lambda/(2*neff);
    }


}
