package com.xute.materialdesign.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xute on 2016/12/7.
 */

public class WaveView extends View {

    private Path mAbovePath, mBelowWavePath;
    private Paint mAboveWavePaint, mBelowWavePaint;
    private DrawFilter mDrawFliter;
    private float degree;

    private OnWaveAnimationListener onWaveAnimationListener;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAbovePath = new Path();
        mBelowWavePath = new Path();

        mAboveWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAboveWavePaint.setAntiAlias(true);
        mAboveWavePaint.setStyle(Paint.Style.FILL);
        mAboveWavePaint.setColor(Color.WHITE);

        mBelowWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBelowWavePaint.setAntiAlias(true);
        mBelowWavePaint.setStyle(Paint.Style.FILL);
        mBelowWavePaint.setColor(Color.WHITE);
        mBelowWavePaint.setAlpha(80);

        mDrawFliter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(mDrawFliter);
        mAbovePath.reset();
        mBelowWavePath.reset();

        degree -= 0.1f;
        float y, y2;
        double w = 2 * Math.PI / getWidth();

        mAbovePath.moveTo(getLeft(), getBottom());
        mBelowWavePath.moveTo(getLeft(), getBottom());

        for (float x = 0; x <= getWidth(); x +=20) {
            y = (float) (8 * Math.cos(w * x + degree) + 8);
            y2 = (float) (8 * Math.sin(w * x + degree));
            mAbovePath.lineTo(x, y);
            mBelowWavePath.lineTo(x, y2);
            onWaveAnimationListener.OnWaveAnimation(y);
        }
        mAbovePath.lineTo(getRight(), getBottom());
        mBelowWavePath.lineTo(getRight(), getBottom());

        canvas.drawPath(mAbovePath, mAboveWavePaint);
        canvas.drawPath(mBelowWavePath, mBelowWavePaint);

        postInvalidateDelayed(20);
    }

    public void setOnWaveAnimationListener(OnWaveAnimationListener listener) {
        onWaveAnimationListener = listener;
    }

    public interface OnWaveAnimationListener {
        void OnWaveAnimation(float y);
    }
}
