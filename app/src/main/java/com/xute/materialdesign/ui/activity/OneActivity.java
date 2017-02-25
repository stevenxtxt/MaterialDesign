package com.xute.materialdesign.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xute.materialdesign.R;
import com.xute.materialdesign.ui.view.CustomPopWindow;
import com.xute.materialdesign.ui.view.WaveView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xute on 2016/12/7.
 */

public class OneActivity extends AppCompatActivity {
    @BindView(R.id.wv_wave) WaveView wvWave;
    @BindView(R.id.iv_icon) ImageView ivIcon;
    @BindView(R.id.btn_popup) Button btnPopup;

    private CustomPopWindow customPopWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);

        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        wvWave.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) (y + 2));
                ivIcon.setLayoutParams(lp);
            }
        });
    }

    @OnClick(R.id.btn_popup)
    public void onClick(View view) {
        customPopWindow = new CustomPopWindow.PopupWindowBuilder(this).setView(R.layout.popup)
                .create().showAdDropDown(btnPopup, 0, 10);
    }
}
