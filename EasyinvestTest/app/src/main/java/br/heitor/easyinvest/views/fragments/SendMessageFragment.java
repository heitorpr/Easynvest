package br.heitor.easyinvest.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.ActivityUtils;
import br.heitor.easyinvest.utils.FragmentNameHelper;
import br.heitor.easyinvest.views.widgets.FontTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SendMessageFragment extends BaseFragment {
    @BindView(R.id.txt_title)
    FontTextView txtTitle;
    @BindView(R.id.txt_label_title)
    FontTextView txtLabelTitle;
    @BindView(R.id.txt_title_investment_fund)
    FontTextView txtTitleInvestmentFund;
    @BindView(R.id.txt_send_new_message)
    FontTextView txtSendNewMessage;

    public SendMessageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();
        setUIEvents();
        return view;
    }

    private void setUIEvents() {
        txtSendNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = getContactFragment(fm);
                String name = FragmentNameHelper.getName(getActivity(), fragment.getClass());

                fm.popBackStack(FragmentNameHelper.getName(getActivity(), SendMessageFragment.class), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ActivityUtils.moveFragment(fragment, fm, true, R.id.content, name, true);
            }
        });
    }

    private Fragment getContactFragment(FragmentManager fm) {
        Fragment fragment = fm.findFragmentByTag(FragmentNameHelper.getName(ctx, ContactFragment.class));

        if (fragment == null) {
            fragment = new ContactFragment();
            fragment.setArguments(new Bundle());
        }

        return fragment;
    }
}
