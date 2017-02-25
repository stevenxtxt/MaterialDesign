package com.xute.materialdesign.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by xute on 2016/12/23.
 */

public class RotateImageView extends ImageView {
    private float mRotateDegrees;
    private int mFrameTime;
    private boolean mNeedToUpdateView;
    private Runnable mUpdateViewRunnable;

    public RotateImageView(Context context) {
        super(context);
        init();
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //setImageResource(R.drawable.kprogresshud_spinner);
        mFrameTime = 1000 / 12;
        mUpdateViewRunnable = new Runnable() {
            @Override
            public void run() {
                mRotateDegrees += 30;
                mRotateDegrees = mRotateDegrees < 360 ? mRotateDegrees : mRotateDegrees - 360;
                invalidate();
                if (mNeedToUpdateView) {
                    postDelayed(this, mFrameTime);
                }
            }
        };
    }


    public void setAnimationSpeed(float scale) {
        mFrameTime = (int) (1000 / 12 / scale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(mRotateDegrees, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mNeedToUpdateView = true;
        post(mUpdateViewRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        mNeedToUpdateView = false;
        super.onDetachedFromWindow();
    }
}
