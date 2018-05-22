package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OutputDateModel
{
    private Double d;
    private Double    neff;
    private Double    p;
    private Double    eps;
    private Double    M;
    private Double    lamdaResult;
    private Double    lamda;

    public OutputDateModel()
    {

    }

    public OutputDateModel(Double d, Double neff, Double p, Double eps, Double m, Double lamdaResult, Double lamda)
    {
        this.d = d;
        this.neff = neff;
        this.p = p;
        this.eps = eps;
        M = m;
        this.lamdaResult = lamdaResult;
        this.lamda = lamda;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getNeff() {
        return neff;
    }

    public void setNeff(Double neff) {
        this.neff = neff;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getM() {
        return M;
    }

    public void setM(Double m) {
        M = m;
    }

    public Double getLamdaResult() {
        return lamdaResult;
    }

    public void setLamdaResult(Double lamdaResult) {
        this.lamdaResult = lamdaResult;
    }

    public Double getLamda() {
        return lamda;
    }

    public void setLamda(Double lamda) {
        this.lamda = lamda;
    }
}
