package br.heitor.easyinvest.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import br.heitor.easyinvest.exceptions.FragmentNameException;
import br.heitor.easyinvest.views.fragments.ContactFragment;
import br.heitor.easyinvest.views.fragments.InvestimentFragment;

public class FragmentNameHelper {
    public static String getName(Context ctx, Class<? extends Fragment> fragment) {
        try {
            return getFragmentName(ctx, fragment);
        } catch (FragmentNameException e) {
            ErrorHandler.logError(e);
        }

        return fragment.getClass().getSimpleName();
    }

    private static String getFragmentName(Context ctx, Class<? extends Fragment> fragment) throws FragmentNameException {
        if (fragment.equals(InvestimentFragment.class)) {
            return InvestimentFragment.class.getSimpleName();
        }

        if (fragment.equals(ContactFragment.class)) {
            return ContactFragment.class.getSimpleName();
        }

        throw new FragmentNameException(ctx, fragment);
    }
}
