package br.heitor.easyinvest.model;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Double> CDI;
    private ArrayList<Double> fund;
    private ArrayList<String> x;

    public ArrayList<Double> getCDI() {
        return this.CDI;
    }

    public void setCDI(ArrayList<Double> CDI) {
        this.CDI = CDI;
    }

    public ArrayList<Double> getFund() {
        return this.fund;
    }

    public void setFund(ArrayList<Double> fund) {
        this.fund = fund;
    }

    public ArrayList<String> getX() {
        return this.x;
    }

    public void setX(ArrayList<String> x) {
        this.x = x;
    }
}
