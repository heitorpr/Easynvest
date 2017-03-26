package br.heitor.easyinvest.model;

import android.app.Activity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.heitor.easyinvest.utils.Utils;

public class InvestmentScreen {
    private InvestmentScreenModel screen;

    public InvestmentScreen() {
    }

    public static InvestmentScreen create(Activity activity) {
        String json = Utils.loadJSONFromAsset(activity, "fund.json");
        return new Gson().fromJson(json, InvestmentScreen.class);
    }

    public InvestmentScreenModel getScreen() {
        return this.screen;
    }

    public void setScreen(InvestmentScreenModel screen) {
        this.screen = screen;
    }

    public String getTitle() {
        return screen.getTitle();
    }

    public String getFundName() {
        return screen.getFundName();
    }

    public String getWhatIs() {
        return screen.getWhatIs();
    }

    public String getDefinition() {
        return screen.getDefinition();
    }

    public String getRiskTitle() {
        return screen.getRiskTitle();
    }

    public String getInfoTitle() {
        return screen.getInfoTitle();
    }

    public int getRisk() {
        return screen.getRisk();
    }

    public MoreInfoObject getMoreInfoOnMonth() {
        return screen.getMoreInfo().getMonth();
    }

    public MoreInfoObject getMoreInfoOnYear() {
        return screen.getMoreInfo().getYear();
    }

    public MoreInfoObject getMoreInfoAllMonths() {
        return screen.getMoreInfo().getAllMonths();
    }

    public ArrayList<Info> getInfo() {
        return screen.getInfo();
    }

    public ArrayList<Info> getDownInfo() {
        return screen.getDownInfo();
    }

    public ArrayList<Float> getGraphFund() {
        return screen.getGraph().getFund();
    }

    public List<Float> getGraphCDI() {
        return screen.getGraph().getCDI();
    }
}
