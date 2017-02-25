package com.xute.materialdesign.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xute.materialdesign.framework.bean.PieChartData;

import java.util.ArrayList;

/**
 * Created by xute on 2017/1/3.
 */

public class PieChartView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    //饼状图初始绘制角度
    private float mStartAngle = 0;

    private ArrayList<PieChartData> mData;

    private int mWidth, mHeight;

    private Paint mPaint = new Paint();

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) {
            return;
        }
        float currentStartAngel = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++ ) {
            PieChartData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rect, currentStartAngel, pie.getAngle(), true, mPaint);
            currentStartAngel += pie.getAngle();
        }
    }

    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setData(ArrayList<PieChartData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    private void initData(ArrayList<PieChartData> mData) {
        if (mData == null || mData.size() == 0) {
            return;
        }
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieChartData pie = mData.get(i);
            sumValue += pie.getValue();
            int j = i % mColors.length;
            pie.setColor(mColors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieChartData pie = mData.get(i);
            float percentage = pie.getValue() / sumValue;
            float angle = 360 * percentage;
            pie.setAngle(angle);
            pie.setPercentage(percentage);
            sumAngle += angle;
        }
    }
}
