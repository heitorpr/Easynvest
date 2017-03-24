package br.heitor.easyinvest.model;

import java.util.ArrayList;

public class InvestmentScreenModel {
    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private Graph graph;
    private String riskTitle;
    private int risk;
    private String infoTitle;
    private MoreInfo moreInfo;
    private ArrayList<Info> info;
    private ArrayList<DownInfo> downInfo;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundName() {
        return this.fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getWhatIs() {
        return this.whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public String getRiskTitle() {
        return this.riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public int getRisk() {
        return this.risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public String getInfoTitle() {
        return this.infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public MoreInfo getMoreInfo() {
        return this.moreInfo;
    }

    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }

    public ArrayList<Info> getInfo() {
        return this.info;
    }

    public void setInfo(ArrayList<Info> info) {
        this.info = info;
    }

    public ArrayList<DownInfo> getDownInfo() {
        return this.downInfo;
    }

    public void setDownInfo(ArrayList<DownInfo> downInfo) {
        this.downInfo = downInfo;
    }
}
