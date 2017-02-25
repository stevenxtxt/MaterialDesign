package com.xute.materialdesign.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by xute on 2016/12/8.
 */

public class CustomPopWindow {
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private boolean mIsFocusable = true;
    private boolean mIsOutside = true;
    private int mResLayoutId = -1;
    private View mContentView;
    private PopupWindow mPopupWindow;
    private int mAnimationStyle = -1;

    private boolean mClippedEnable = true;
    private boolean mIgnoreCheekPress = false;
    private int mInputMode = -1;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private int mSoftInputMode = -1;
    private boolean mTouchable = true;
    private View.OnTouchListener mOnTouchListner;

    private CustomPopWindow(Context context) {
        mContext = context;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    /**
     *
     * @param anchor
     * @param xOff
     * @param yOff
     * @return
     */
    public CustomPopWindow showAdDropDown(View anchor, int xOff, int yOff) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, xOff, yOff);
        }
        return this;
    }

    /**
     *
     * @param anchor
     * @return
     */
    public CustomPopWindow showAdDropDown(View anchor) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor);
        }
        return this;
    }

    /**
     *
     * @param anchor
     * @param xOff
     * @param yOff
     * @param gravity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CustomPopWindow showAdDropDown(View anchor, int xOff, int yOff, int gravity) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, xOff, yOff, gravity);
        }
        return this;
    }

    public CustomPopWindow showAtLocation(View parent, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(parent, gravity, x, y);
        }
        return this;
    }

    private void apply(PopupWindow popupWindow) {
        popupWindow.setClippingEnabled(mClippedEnable);
        if (mIgnoreCheekPress) {
            popupWindow.setIgnoreCheekPress();
        }
        if (mInputMode != -1) {
            popupWindow.setInputMethodMode(mInputMode);
        }
        if (mSoftInputMode != -1) {
            popupWindow.setSoftInputMode(mSoftInputMode);
        }
        if (mOnDismissListener != null) {
            popupWindow.setOnDismissListener(mOnDismissListener);
        }
        if (mOnTouchListner != null) {
            popupWindow.setTouchInterceptor(mOnTouchListner);
        }
        popupWindow.setTouchable(mTouchable);
    }

    private PopupWindow build() {
        if (mContentView == null) {
            mContentView = LayoutInflater.from(mContext).inflate(mResLayoutId, null);
        }
        if (mWidth != 0 && mHeight != 0) {
            mPopupWindow = new PopupWindow(mContentView, mWidth, mHeight);
        } else {
            mPopupWindow = new PopupWindow(mContentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (mAnimationStyle != -1) {
            mPopupWindow.setAnimationStyle(mAnimationStyle);
        }
        apply(mPopupWindow);

        mPopupWindow.setFocusable(mIsFocusable);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(mIsOutside);

        if (mWidth == 0 || mHeight == 0) {
            mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mWidth = mPopupWindow.getContentView().getMeasuredWidth();
            mHeight = mPopupWindow.getContentView().getMeasuredHeight();
        }

        mPopupWindow.update();
        return mPopupWindow;
    }

    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    public static class PopupWindowBuilder {
        private CustomPopWindow mCustomPopWindow;

        public PopupWindowBuilder(Context context) {
            mCustomPopWindow = new CustomPopWindow(context);
        }
        public PopupWindowBuilder size(int width, int height) {
            mCustomPopWindow.mWidth = width;
            mCustomPopWindow.mHeight = height;
            return this;
        }

        public PopupWindowBuilder setFocusable(boolean focusable) {
            mCustomPopWindow.mIsFocusable = focusable;
            return this;
        }

        public PopupWindowBuilder setView(int resLayoutId) {
            mCustomPopWindow.mResLayoutId = resLayoutId;
            mCustomPopWindow.mContentView = null;
            return this;
        }

        public PopupWindowBuilder setView(View view) {
            mCustomPopWindow.mContentView = view;
            mCustomPopWindow.mResLayoutId = -1;
            return this;
        }

        public PopupWindowBuilder setOutsideTouchable(boolean outsideTouchable) {
            mCustomPopWindow.mIsOutside = outsideTouchable;
            return this;
        }

        public PopupWindowBuilder setAnimationStyle(int animationStyle) {
            mCustomPopWindow.mAnimationStyle = animationStyle;
            return this;
        }

        public PopupWindowBuilder setClippingEnable(boolean enable) {
            mCustomPopWindow.mClippedEnable = enable;
            return this;
        }

        public PopupWindowBuilder setIgnoreCheekPress(boolean ignoreCheekPress) {
            mCustomPopWindow.mIgnoreCheekPress = ignoreCheekPress;
            return this;
        }

        public PopupWindowBuilder setInputMethodMode(int mode) {
            mCustomPopWindow.mInputMode = mode;
            return this;
        }

        public PopupWindowBuilder setSoftInputMode(int softInputMode) {
            mCustomPopWindow.mSoftInputMode = softInputMode;
            return this;
        }

        public PopupWindowBuilder setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
            mCustomPopWindow.mOnDismissListener = onDismissListener;
            return this;
        }

        public PopupWindowBuilder setTouchable(boolean touchable) {
            mCustomPopWindow.mTouchable = touchable;
            return this;
        }

        public PopupWindowBuilder setTouchIntercepter(View.OnTouchListener touchIntercepter) {
            mCustomPopWindow.mOnTouchListner = touchIntercepter;
            return this;
        }

        public CustomPopWindow create() {
            mCustomPopWindow.build();
            return mCustomPopWindow;
        }
    }
}
