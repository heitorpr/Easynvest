package br.heitor.easyinvest.model;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Float> CDI;
    private ArrayList<Float> fund;
    private ArrayList<String> x;

    public ArrayList<Float> getCDI() {
        return this.CDI;
    }

    public void setCDI(ArrayList<Float> CDI) {
        this.CDI = CDI;
    }

    public ArrayList<Float> getFund() {
        return this.fund;
    }

    public void setFund(ArrayList<Float> fund) {
        this.fund = fund;
    }

    public ArrayList<String> getX() {
        return this.x;
    }

    public void setX(ArrayList<String> x) {
        this.x = x;
    }
}
