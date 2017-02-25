package com.xute.materialdesign.ui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xute.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xute on 2016/12/8.
 */

public class MyProgressBar extends FrameLayout {

    private int startIndex = 0;

    private int[] src = new int[] {R.drawable.dot_accent, R.drawable.dot_primary, R.drawable.dot_primarydark};

    private List<ImageView> views = new ArrayList<>();

    private AnimatorSet animatorSet;

    private int maxRadius = 100;

    public MyProgressBar(Context context) {
        super(context);
        init();
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_progress_bar, this, true);
        assignView();
        startAnimator();
    }

    private void assignView() {
        ImageView iv_dot1 = (ImageView) findViewById(R.id.iv_dot1);
        ImageView iv_dot2 = (ImageView) findViewById(R.id.iv_dot2);
        ImageView iv_dot3 = (ImageView) findViewById(R.id.iv_dot3);
        views.add(iv_dot1);
        views.add(iv_dot2);
        views.add(iv_dot3);
    }

    private void startAnimator() {
        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(views.get(0), "translationX", 0, -maxRadius, 0);
        objectAnimatorLeft.setRepeatCount(-1);
        objectAnimatorLeft.setDuration(1000);

        ObjectAnimator objectAnimatorRight = ObjectAnimator.ofFloat(views.get(1), "translationX", 0, maxRadius, 0);
        objectAnimatorRight.setRepeatCount(-1);
        objectAnimatorRight.setDuration(1000);

        animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorLeft).with(objectAnimatorRight);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();

        objectAnimatorLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                if (startIndex == 0) {
                    sweep(0, 2);
                    startIndex = 1;
                }else {
                    sweep(1, 2);
                    startIndex = 0;
                }
            }
        });
    }

    private void sweep(int a, int b) {
        views.get(a).setImageResource(src[b]);
        views.get(b).setImageResource(src[a]);
        int temp = src[b];
        src[b] = src[a];
        src[a] = temp;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorSet.cancel();
    }
}
