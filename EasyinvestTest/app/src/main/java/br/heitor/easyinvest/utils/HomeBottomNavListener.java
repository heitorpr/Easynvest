package br.heitor.easyinvest.utils;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.LinearLayout;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.views.widgets.FontTextView;

public class HomeBottomNavListener implements View.OnClickListener {

    private FontTextView mTextMessage;
    private int selected;

    public HomeBottomNavListener(FontTextView mTextMessage, View selectedView) {
        this.mTextMessage = mTextMessage;
        this.selected = selectedView.getId();

        selectView(selectedView, 0);
    }

    @Override
    public void onClick(View v) {
        if (selected == v.getId()) return;
        selected = v.getId();

        if (v.getId() == R.id.btnNavInvestment) {
            mTextMessage.setText(R.string.title_home);
            selectView(v, 200);
            return;
        }

        if (v.getId() != R.id.btnNavContact) return;

        mTextMessage.setText(R.string.title_dashboard);
        selectView(v, 200);
    }

    private void selectView(View v, int time) {
        TransitionDrawable transition = (TransitionDrawable) v.getBackground();
        transition.startTransition(time);

        if (time == 0) return;
        LinearLayout view = (LinearLayout) v.getParent();

        if (v.getId() == R.id.btnNavInvestment) {
            transition = (TransitionDrawable) view.findViewById(R.id.btnNavContact).getBackground();
        }

        if (v.getId() == R.id.btnNavContact) {
            transition = (TransitionDrawable) view.findViewById(R.id.btnNavInvestment).getBackground();
        }

        transition.reverseTransition(0);
    }
}
