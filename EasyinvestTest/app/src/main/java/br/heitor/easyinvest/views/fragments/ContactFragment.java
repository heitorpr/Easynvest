package br.heitor.easyinvest.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.model.CellScreen;
import br.heitor.easyinvest.utils.ActivityUtils;
import br.heitor.easyinvest.utils.FragmentNameHelper;
import br.heitor.easyinvest.utils.Utils;
import br.heitor.easyinvest.views.widgets.FontButtonView;
import br.heitor.easyinvest.views.widgets.FontCheckBoxView;
import br.heitor.easyinvest.views.widgets.FontEditView;
import br.heitor.easyinvest.views.widgets.FontTextInputLayout;
import br.heitor.easyinvest.views.widgets.FontTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactFragment extends BaseFragment {
    @BindView(R.id.txt_title)
    FontTextView txtTitle;
    @BindView(R.id.txt_name)
    FontEditView txtName;
    @BindView(R.id.inputLayout_name)
    FontTextInputLayout inputLayoutName;
    @BindView(R.id.txt_email)
    FontEditView txtEmail;
    @BindView(R.id.inputLayout_email)
    FontTextInputLayout inputLayoutEmail;
    @BindView(R.id.txt_telephone)
    FontEditView txtTelephone;
    @BindView(R.id.inputLayout_telephone)
    FontTextInputLayout inputLayoutTelephone;
    @BindView(R.id.img_blank)
    ImageView imgBlank;
    @BindView(R.id.btn_checkbox)
    FontCheckBoxView btnCheckbox;
    @BindView(R.id.btn_send)
    FontButtonView btnSend;

    private CellScreen cellModel;

    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();
        setUIEvents();
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();

        if (cellModel == null) cellModel = CellScreen.create(getActivity());
        setViews();
    }

    private void setViews() {
        txtTelephone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void setUIEvents() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtName.is_valid() || !txtTelephone.is_valid() || !txtEmail.is_valid()) {
                    Toast.makeText(ctx, R.string.error_fields, Toast.LENGTH_LONG).show();
                    return;
                }

                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = getSendMessageFragment(fm);
                String name = FragmentNameHelper.getName(getActivity(), fragment.getClass());

                fm.popBackStack(FragmentNameHelper.getName(getActivity(), ContactFragment.class), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ActivityUtils.moveFragment(fragment, fm, true, R.id.content, name, true);

            }
        });
    }

    private Fragment getSendMessageFragment(FragmentManager fm) {
        Fragment fragment = fm.findFragmentByTag(FragmentNameHelper.getName(ctx, SendMessageFragment.class));

        if (fragment == null) {
            fragment = new SendMessageFragment();
            fragment.setArguments(new Bundle());
        }

        return fragment;
    }
}
