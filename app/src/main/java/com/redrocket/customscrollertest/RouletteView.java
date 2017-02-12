package com.redrocket.customscrollertest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class RouletteView extends View {

    private static final int BLOCK_WIDTH_PX = 40;
    private static final int STICK_HEIGHT_PX = 10;
    private static final int STICK_WIDTH_PX = 5;


    private Scroller mScroller;
    private ValueAnimator mScrollAnimator;
    private GestureDetector mDetector;


    private int mLength;

    private Paint mPaint;


    public RouletteView(Context context) {
        super(context);
        init();
    }

    public RouletteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RouletteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext(), null, true);

        mScrollAnimator = ValueAnimator.ofFloat(0, 1);
        mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tickScrollAnimation();
            }
        });

        mDetector = new GestureDetector(getContext(), new GestureListener());
        mDetector.setIsLongpressEnabled(false);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

    }

    private void tickScrollAnimation() {
        if (!mScroller.isFinished()) {
            mScroller.computeScrollOffset();
            Log.i("DEBUG","tickScrollAnimation "+mScroller.getCurrX());
            //scrollTo(mScroller.getCurrX(), 0);
        } else {
            mScrollAnimator.cancel();
            //onScrollFinished();

        }

        scrollTo(mScroller.getCurrX(), 0);

    }

    private void stopScrolling() {
        mScroller.forceFinished(true);
        //onScrollFinished();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("DEBUG", "onDraw "+canvas.getWidth());
        super.onDraw(canvas);
        //drawBlock(0,canvas,0);
        long timeStart = System.currentTimeMillis();

        int first = getFirstVisibleBlock();
        int last = getLastVisibleBlock(canvas);
        drawBlockPack(first,last,canvas);

        long timeFinish = System.currentTimeMillis();
        Log.i("DEBUG", "draw time " + (timeFinish - timeStart));
    }

    private int getFirstVisibleBlock() {
        int index = getScrollX() / BLOCK_WIDTH_PX;
        return index;
    }

    private int getLastVisibleBlock(Canvas canvas) {
        int index = (getScrollX() + canvas.getWidth()) / BLOCK_WIDTH_PX + 1;
        return index;
    }

    private void drawBlockPack(int firstIndex, int lastIndex, Canvas canvas) {
        for (int i = firstIndex; i < lastIndex; i++){
            drawBlock(i, canvas, i * BLOCK_WIDTH_PX);
        }
    }

    private void drawBlock(int index, Canvas canvas, int startX) {
        //Log.i("DEBUG", "drawBlock " + startX);
        canvas.drawRect(startX,
                canvas.getHeight() - STICK_HEIGHT_PX, startX + STICK_WIDTH_PX,
                canvas.getHeight(), mPaint);

        canvas.drawText(String.valueOf(index),startX,canvas.getHeight() - STICK_HEIGHT_PX - 5,mPaint);
    }

    public void setLength(final int length) {
        mLength = length;
    }

    public void addData(int[] data) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void scrollTo(int x, int y) {
        Log.i("DEBUG", this + " scrollTo " + x + " " + y);
        super.scrollTo(x, y);
        invalidate();
    }

    @Override
    public void scrollBy(int x, int y) {
        Log.i("DEBUG", this + " scrollBy " + x + " " + y);
        super.scrollBy(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Let the GestureDetector interpret this event
        boolean result = mDetector.onTouchEvent(event);

        // If the GestureDetector doesn't want this event, do some custom processing.
        // This code just tries to detect when the user is done scrolling by looking
        // for ACTION_UP events.
        if (!result) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // User is done scrolling, it's now safe to do things like autocenter
                stopScrolling();
                result = true;
            }
        }
        return result;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (getScrollX() + distanceX > 0)
                scrollTo((int) (getScrollX() + distanceX), 0);

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // Set up the Scroller for a fling

            mScroller.fling(
                    getScrollX(),
                    0,
                    -(int)velocityX,
                    0,
                    0,
                    Integer.MAX_VALUE,
                    0,
                    0
                    );

            Log.i("DEBUG","onFling "+mScroller.getDuration());

                mScrollAnimator.setDuration(mScroller.getDuration());
                mScrollAnimator.start();

            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {

            if (!mScroller.isFinished())
                stopScrolling();

            return true;
        }
    }


}
