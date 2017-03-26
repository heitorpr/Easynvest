package br.heitor.easyinvest.model;

import android.app.Activity;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.heitor.easyinvest.utils.Utils;

public class CellScreen {
    private ArrayList<CellModel> cells;

    public static CellScreen create(Activity activity) {
        String json = Utils.loadJSONFromAsset(activity, "cells.json");
        return new Gson().fromJson(json, CellScreen.class);
    }

    public ArrayList<CellModel> getCells() {
        return this.cells;
    }

    public void setCells(ArrayList<CellModel> cells) {
        this.cells = cells;
    }
}
