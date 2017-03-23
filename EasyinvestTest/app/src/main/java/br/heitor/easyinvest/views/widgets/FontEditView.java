package br.heitor.easyinvest.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.FontManager;

public class FontEditView extends TextInputEditText {
    public FontEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        // Fonts work as a combination of particular family and the style.
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Fonts);
        String family = a.getString(R.styleable.Fonts_fontFamily);
        FontManager.FontStyleEnum fontStyleEnum = FontManager.FontStyleEnum.fromId(a.getInt(R.styleable.Fonts_fontStyle, 0));
        a.recycle();

        // Set the typeface based on the family and the style combination.
        setTypeface(FontManager.getInstance().get(family, fontStyleEnum));
    }

    public void setError(boolean is_error) {
        int pL = getPaddingLeft();
        int pT = getPaddingTop();
        int pR = getPaddingRight();
        int pB = getPaddingBottom();

        if (is_error) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.edit_background_error));
            setPadding(pL, pT, pR, pB);
            return;
        }

        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.edit_background));
        setPadding(pL, pT, pR, pB);
    }

    public void setSuccess(boolean is_success) {
        int pL = getPaddingLeft();
        int pT = getPaddingTop();
        int pR = getPaddingRight();
        int pB = getPaddingBottom();

        if (is_success) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.edit_background_success));
            setPadding(pL, pT, pR, pB);
            return;
        }

        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.edit_background));
        setPadding(pL, pT, pR, pB);
    }
}
