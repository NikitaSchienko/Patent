package pojo;

import javafx.scene.control.CheckBox;

public class ModelOutputMode
{
    private int number;
    private double l1;
    private double l2;
    private double l3;
    private double R;
    private double U;
    private double L;
    private double E;
    private double F;
    private double deformation;
    private CheckBox check;


    public ModelOutputMode(int number, double l1, double l2, double l3, double r, double U, double l, double e, double f, double deformation, CheckBox check) {
        this.number = number;
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.check = check;
        R = r;
        this.U = U;
        this.deformation = deformation;
        L = l;
        E = e;
        F = f;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getL1() {
        return l1;
    }

    public void setL1(double l1) {
        this.l1 = l1;
    }

    public double getL2() {
        return l2;
    }

    public void setL2(double l2) {
        this.l2 = l2;
    }

    public double getL3() {
        return l3;
    }

    public void setL3(double l3) {
        this.l3 = l3;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getU() {
        return U;
    }

    public void setU(double u) {
        U = u;
    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getE() {
        return E;
    }

    public void setE(double e) {
        E = e;
    }

    public double getF() {
        return F;
    }

    public void setF(double f) {
        F = f;
    }

    public double getDeformation() {
        return deformation;
    }

    public void setDeformation(double deformation) {
        this.deformation = deformation;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }
}
