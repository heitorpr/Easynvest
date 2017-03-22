package br.heitor.easyinvest.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.ActivityNameHelper;
import br.heitor.easyinvest.utils.FontManager;
import butterknife.Unbinder;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    protected Context ctx;
    protected Unbinder unbinder;
    protected String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        TAG = ActivityNameHelper.getName(ctx, this);
        FontManager.getInstance().initialize(this, R.xml.fonts);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
}
