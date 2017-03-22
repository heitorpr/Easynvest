package br.heitor.easyinvest.views.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.widget.FrameLayout;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.FontManager;
import br.heitor.easyinvest.utils.HomeBottomNavListener;
import br.heitor.easyinvest.views.widgets.FontTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.message)
    FontTextView mTextMessage;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.content)
    FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(HomeBottomNavListener.getListener(this, mTextMessage));
    }
}
