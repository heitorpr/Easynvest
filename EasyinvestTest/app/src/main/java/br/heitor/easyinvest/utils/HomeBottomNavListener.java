package br.heitor.easyinvest.utils;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.views.fragments.ContactFragment;
import br.heitor.easyinvest.views.fragments.InvestmentFragment;

public class HomeBottomNavListener implements View.OnClickListener {
    private FrameLayout content;
    private AppCompatActivity activity;

    private int selected;

    public HomeBottomNavListener(AppCompatActivity activity, FrameLayout content, View selectedView) {
        this.activity = activity;
        this.content = content;
        this.selected = selectedView.getId();

        selectView(selectedView, 0);
    }

    @NonNull
    private static Fragment getFragment(Context ctx, int itemId, FragmentManager fm) {
        if (itemId == R.id.btnNavInvestment) {
            Fragment fragment = fm.findFragmentByTag(FragmentNameHelper.getName(ctx, InvestmentFragment.class));

            if (fragment == null) {
                fragment = new InvestmentFragment();
                fragment.setArguments(new Bundle());
            }

            return fragment;
        }

        Fragment fragment;
        fragment = fm.findFragmentByTag(FragmentNameHelper.getName(ctx, ContactFragment.class));

        if (fragment == null) {
            fragment = new ContactFragment();
            fragment.setArguments(new Bundle());
        }

        return fragment;
    }

    public void openFirstFragment() {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = getFragment(activity, R.id.btnNavInvestment, fm);
        String name = FragmentNameHelper.getName(activity, fragment.getClass());
        fragment.setHasOptionsMenu(true);

        ActivityUtils.moveFragment(fragment, fm, false, content.getId(), name, true);
    }

    @Override
    public void onClick(View v) {
        if (selected == v.getId()) return;
        selected = v.getId();

        if (v.getId() == R.id.btnNavInvestment) {
            FragmentManager fm = activity.getSupportFragmentManager();
            Fragment fragment = getFragment(activity, v.getId(), fm);
            String name = FragmentNameHelper.getName(activity, fragment.getClass());

            ActivityUtils.moveFragment(fragment, fm, false, content.getId(), name, true);
            selectView(v, 200);
            return;
        }

        if (v.getId() != R.id.btnNavContact) return;
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = getFragment(activity, v.getId(), fm);
        String name = FragmentNameHelper.getName(activity, fragment.getClass());

        ActivityUtils.moveFragment(fragment, fm, false, content.getId(), name, true);
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

    public void updateByBackButton(View btnNavInvestment, View btnNavContact) {
        if (selected == btnNavInvestment.getId()) {
            selected = R.id.btnNavContact;
            selectView(btnNavContact, 200);
            return;
        }

        selected = R.id.btnNavInvestment;
        selectView(btnNavInvestment, 200);
    }
}
