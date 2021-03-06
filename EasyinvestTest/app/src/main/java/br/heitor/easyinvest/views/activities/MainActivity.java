package br.heitor.easyinvest.views.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.HomeBottomNavListener;
import br.heitor.easyinvest.views.widgets.FontButtonView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.btnNavInvestment)
    FontButtonView btnNavInvestment;
    @BindView(R.id.btnNavContact)
    FontButtonView btnNavContact;
    @BindView(R.id.navigation)
    LinearLayout navigation;

    HomeBottomNavListener bottomNavListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        bottomNavListener = new HomeBottomNavListener(this, content, btnNavInvestment);
        bottomNavListener.openFirstFragment();

        btnNavInvestment.setOnClickListener(bottomNavListener);
        btnNavContact.setOnClickListener(bottomNavListener);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
            bottomNavListener.updateByBackButton(btnNavInvestment, btnNavContact);
            return;
        }

        finish();
    }
}
