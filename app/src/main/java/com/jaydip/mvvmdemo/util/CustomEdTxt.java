package com.jaydip.mvvmdemo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.jaydip.mvvmdemo.R;


public class CustomEdTxt extends androidx.appcompat.widget.AppCompatEditText {


    public CustomEdTxt(Context context) {
        super(context);
    }

    public CustomEdTxt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEdTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Drawable getBackground() {
        return ContextCompat.getDrawable(getContext(), R.drawable.shape_edtv_background);
    }
}