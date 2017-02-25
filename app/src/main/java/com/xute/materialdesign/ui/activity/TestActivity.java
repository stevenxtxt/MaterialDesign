package com.xute.materialdesign.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xute.materialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xute on 2016/11/24.
 */

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.flb_btn) FloatingActionButton flbBtn;

    private Snackbar snackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.flb_btn)
    public void onClick(View view) {
        snackbar = Snackbar.make(view, "FAB", Snackbar.LENGTH_LONG);
        snackbar.setAction("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
