package br.heitor.easyinvest.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.utils.FontManager;

public class FontEditView extends TextInputEditText {
    boolean isUsingDrawable;
    int actionX, actionY;
    private Drawable drawableRight;

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

        addListener();
        checkDrawableRight(getText());
    }

    private void addListener() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkDrawableRight(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkDrawableRight(CharSequence s) {
        if (isUsingDrawable && s.length() == 0) {
            isUsingDrawable = false;
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            return;
        }

        if (!isUsingDrawable && s.length() > 0) {
            isUsingDrawable = true;
            setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getContext(), R.drawable.ic_cancel_black_20dp), null);
        }
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (right != null) drawableRight = right;
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Rect bounds;
        if (event.getAction() != MotionEvent.ACTION_DOWN) return super.onTouchEvent(event);
        if (drawableRight == null) return super.onTouchEvent(event);

        actionX = (int) event.getX();
        actionY = (int) event.getY();
        bounds = drawableRight.getBounds();

        int x, y;
        int extraTapArea = 13;

        /**
         * IF USER CLICKS JUST OUT SIDE THE RECTANGLE OF THE DRAWABLE
         * THAN ADD X AND SUBTRACT THE Y WITH SOME VALUE SO THAT AFTER
         * CALCULATING X AND Y CO-ORDINATE LIES INTO THE DRAWBABLE
         * BOUND. - this process help to increase the tappable area of
         * the rectangle.
         */

        x = (actionX + extraTapArea);
        y = (actionY - extraTapArea);

        /**Since this is right drawable subtract the value of x from the width
         * of view. so that width - tappedarea will result in x co-ordinate in drawable bound.
         */

        x = getWidth() - x;

         /*x can be negative if user taps at x co-ordinate just near the width.
         * e.g views width = 300 and user taps 290. Then as per previous calculation
         * 290 + 13 = 303. So subtract X from getWidth() will result in negative value.
         * So to avoid this add the value previous added when x goes negative.
         */

        if (x <= 0) x += extraTapArea;

         /* If result after calculating for extra tappable area is negative.
         * assign the original value so that after subtracting
         * extratapping area value doesn't go into negative value.
         */

        if (y <= 0) y = actionY;

        /* If drawble bounds contains the x and y points then move ahead.*/
        if (bounds.contains(x, y)) {
            setText("");
            event.setAction(MotionEvent.ACTION_CANCEL);
            return false;
        }

        return super.onTouchEvent(event);
    }
}
