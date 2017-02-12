package com.redrocket.customscrollertest;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Bob on 2/11/2017.
 */

public class CustomImageView extends ImageView{


    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Rect bounds = canvas.getClipBounds();
        Log.i("DEBUG", this + " onDraw " + canvas.getWidth() + " " + canvas.getHeight()
                + " bounds " + bounds.left + " " + bounds.top + " " + bounds.right + " " + bounds.bottom);
        super.onDraw(canvas);

    }

    @Override
    public void scrollTo(int x, int y) {
        Log.i("DEBUG", this + " scrollTo " + x + " " + y);
        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        Log.i("DEBUG", this + " scrollBy " + x + " " + y);
        super.scrollBy(x, y);
    }


}
