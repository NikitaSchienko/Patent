package models;

import javafx.fxml.FXML;

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;

@XmlRootElement
public class InputDateModel
{
    private Double dElectret;
    private Double    epsilon;
    private Double    l2;
    private Double    l3;
    private Double   l1;
    private Double    p_;
    private Double    U;
    private Double    M;
    private Double    d;
    private Double    Lf;
    private Double   neff;
    private Double    period;

    public InputDateModel()
    {

    }


    public InputDateModel(Double dElectret, Double epsilon, Double l2, Double l3, Double l1, Double p_, Double u, Double m, Double d, Double lf, Double neff, Double period) {
        this.dElectret = dElectret;
        this.epsilon = epsilon;
        this.l2 = l2;
        this.l3 = l3;
        this.l1 = l1;
        this.p_ = p_;
        U = u;
        M = m;
        this.d = d;
        Lf = lf;
        this.neff = neff;
        this.period = period;
    }

    public Double getdElectret() {
        return dElectret;
    }

    public void setdElectret(Double dElectret) {
        this.dElectret = dElectret;
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }

    public Double getL2() {
        return l2;
    }

    public void setL2(Double l2) {
        this.l2 = l2;
    }

    public Double getL3() {
        return l3;
    }

    public void setL3(Double l3) {
        this.l3 = l3;
    }

    public Double getL1() {
        return l1;
    }

    public void setL1(Double l1) {
        this.l1 = l1;
    }

    public Double getP_() {
        return p_;
    }

    public void setP_(Double p_) {
        this.p_ = p_;
    }

    public Double getU() {
        return U;
    }

    public void setU(Double u) {
        U = u;
    }

    public Double getM() {
        return M;
    }

    public void setM(Double m) {
        M = m;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getLf() {
        return Lf;
    }

    public void setLf(Double lf) {
        Lf = lf;
    }

    public Double getNeff() {
        return neff;
    }

    public void setNeff(Double neff) {
        this.neff = neff;
    }

    public Double getPeriod() {
        return period;
    }

    public void setPeriod(Double period) {
        this.period = period;
    }
}
