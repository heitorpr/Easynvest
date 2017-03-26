package br.heitor.easyinvest.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.heitor.easyinvest.R;

public class ActivityUtils {
    public static void moveFragment(Fragment fragment, FragmentManager fm, boolean animate, int frameID, String name, boolean addToBackStack) {
        FragmentTransaction transactionFragment = fm.beginTransaction();
        if (animate) setAnimationOnTransaction(transactionFragment);

        transactionFragment.replace(frameID, fragment, name);

        if (addToBackStack) transactionFragment.addToBackStack(name);

        transactionFragment.commit();
    }

    private static void setAnimationOnTransaction(FragmentTransaction transactionFragment) {
        transactionFragment.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
