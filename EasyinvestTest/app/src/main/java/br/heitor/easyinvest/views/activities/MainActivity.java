package br.heitor.easyinvest.views.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.HomeBottomNavListener;
import br.heitor.easyinvest.views.widgets.FontButtonView;
import br.heitor.easyinvest.views.widgets.FontTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.message)
    FontTextView mTextMessage;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.btnTest)
    FontButtonView btnTest;
    @BindView(R.id.btnNavInvestment)
    FontButtonView btnNavInvestment;
    @BindView(R.id.btnNavContact)
    FontButtonView btnNavContact;
    @BindView(R.id.navigation)
    LinearLayout navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        HomeBottomNavListener bottomNavListener = new HomeBottomNavListener(mTextMessage, btnNavInvestment);

        btnNavInvestment.setOnClickListener(bottomNavListener);
        btnNavContact.setOnClickListener(bottomNavListener);
    }
}
