package br.heitor.easyinvest.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.views.widgets.FontTextView;

public class HomeBottomNavListener {
    public static BottomNavigationView.OnNavigationItemSelectedListener getListener(final AppCompatActivity activity, final FontTextView mTextMessage) {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_dashboard:
                        mTextMessage.setText(R.string.title_dashboard);
                        return true;
                    case R.id.navigation_notifications:
                        mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                return false;
            }

        };
    }
}
