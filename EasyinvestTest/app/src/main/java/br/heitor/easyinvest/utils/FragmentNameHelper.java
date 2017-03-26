package br.heitor.easyinvest.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import br.heitor.easyinvest.exceptions.FragmentNameException;
import br.heitor.easyinvest.views.fragments.ContactFragment;
import br.heitor.easyinvest.views.fragments.InvestmentFragment;
import br.heitor.easyinvest.views.fragments.SendMessageFragment;

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
        if (fragment.equals(InvestmentFragment.class)) {
            return InvestmentFragment.class.getSimpleName();
        }

        if (fragment.equals(ContactFragment.class)) {
            return ContactFragment.class.getSimpleName();
        }

        if (fragment.equals(SendMessageFragment.class)) {
            return SendMessageFragment.class.getSimpleName();
        }

        throw new FragmentNameException(ctx, fragment);
    }
}
