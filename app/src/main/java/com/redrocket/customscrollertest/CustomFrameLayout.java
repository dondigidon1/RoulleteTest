package com.redrocket.customscrollertest;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class CustomFrameLayout extends FrameLayout {
    public CustomFrameLayout(Context context) {
        super(context);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("DEBUG","FrameLayout onDraw ");
        super.onDraw(canvas);

    }

    @Override
    public void scrollBy(int x, int y) {
        Log.i("DEBUG","scrollBy ");
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        Log.i("DEBUG","scrollTo ");
        super.scrollTo(x, y);
    }
}
