package com.xute.materialdesign.ui.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xute on 2016/12/9.
 */

public class MyLinearLayout extends LinearLayout {

    private ViewDragHelper mViewDragHelper;

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewDragHelper();
    }

    private void initViewDragHelper() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int fixedLeft;
                View parent = (View) child.getParent();
                int leftBound = parent.getPaddingLeft();
                int rightBound = parent.getWidth() - child.getWidth() - parent.getPaddingRight();

                if (left < leftBound) {
                    fixedLeft = leftBound;
                } else if (left > rightBound) {
                    fixedLeft = rightBound;
                } else {
                    fixedLeft = left;
                }
                return fixedLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int fixedTop;
                View parent = (View) child.getParent();
                int topBound = parent.getPaddingTop();
                int bottomBound = getHeight() - child.getHeight() - parent.getPaddingBottom();

                if (top < topBound) {
                    fixedTop = topBound;
                } else if (top > bottomBound) {
                    fixedTop = bottomBound;
                } else {
                    fixedTop = top;
                }
                return fixedTop;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                switch (state) {
                    case ViewDragHelper.STATE_DRAGGING:
                        System.out.println("STATE_DRAGGING");
                        break;
                    case ViewDragHelper.STATE_IDLE:
                        System.out.println("STATE_IDLE");
                        break;
                    case ViewDragHelper.STATE_SETTLING:
                        System.out.println("STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                System.out.println("ViewCaptured");
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                System.out.println("ViewReleased");
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
