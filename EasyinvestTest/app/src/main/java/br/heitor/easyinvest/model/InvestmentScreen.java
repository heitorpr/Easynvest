package br.heitor.easyinvest.model;

import android.app.Activity;

import com.google.gson.Gson;

import br.heitor.easyinvest.utils.Utils;

public class InvestmentScreen {
    private InvestmentScreenModel screen;

    public InvestmentScreen() {
    }

    public static InvestmentScreen create(Activity activity) {
        String json = Utils.loadJSONFromAsset(activity, "fund.json");
        return new Gson().fromJson(json, InvestmentScreen.class);
    }

    public InvestmentScreenModel getScreen() { return this.screen; }

    public void setScreen(InvestmentScreenModel screen) { this.screen = screen; }
}
