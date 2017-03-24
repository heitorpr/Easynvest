package br.heitor.easyinvest.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.model.InvestmentScreen;
import butterknife.ButterKnife;

public class InvestimentFragment extends BaseFragment {
    private InvestmentScreen investmentModel;

    public InvestimentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investiment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();

        if (investmentModel == null) investmentModel = InvestmentScreen.create(getActivity());
    }

}
