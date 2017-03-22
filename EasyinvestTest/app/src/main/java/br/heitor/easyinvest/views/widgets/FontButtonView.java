package br.heitor.easyinvest.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.FontManager;

public class FontButtonView extends AppCompatButton {
    public FontButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) init(context, attrs);
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
}
