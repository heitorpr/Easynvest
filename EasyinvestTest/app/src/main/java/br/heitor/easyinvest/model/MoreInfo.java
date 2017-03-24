package br.heitor.easyinvest.model;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {
    private MoreInfoObject month;
    private MoreInfoObject year;
    @SerializedName("12months")
    private MoreInfoObject allMonths;

    public MoreInfoObject getMonth() {
        return month;
    }

    public void setMonth(MoreInfoObject month) {
        this.month = month;
    }

    public MoreInfoObject getYear() {
        return year;
    }

    public void setYear(MoreInfoObject year) {
        this.year = year;
    }

    public MoreInfoObject getAllMonths() {
        return allMonths;
    }

    public void setAllMonths(MoreInfoObject allMonths) {
        this.allMonths = allMonths;
    }
}