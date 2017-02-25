package com.xute.materialdesign.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xute.materialdesign.R;
import com.xute.materialdesign.framework.bean.PieChartData;
import com.xute.materialdesign.ui.view.PieChartView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xute on 2017/1/11.
 */

public class ThreeActivity extends AppCompatActivity {
    @BindView(R.id.pcv_pie) PieChartView pcvPie;
    private ArrayList<PieChartData> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ButterKnife.bind(this);

        for (int i = 1; i < 5; i++) {
            PieChartData data = new PieChartData();
            data.setName(i + "");
            data.setValue(i * 10);
            mData.add(data);
        }

        pcvPie.setData(mData);
    }
}
