package com.xute.materialdesign.ui.activity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.xute.materialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xute on 2016/11/25.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.iv_image) ImageView ivImage;
    @BindView(R.id.ctl_tool) CollapsingToolbarLayout ctlTool;
    @BindView(R.id.tv_detail) TextView tvDetail;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.cl_detail) CoordinatorLayout clDetail;

    public  static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(R.id.tb_tool));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Detail");
        }

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.places);
        ctlTool.setTitle(places[position % places.length]);

        String[] details = resources.getStringArray(R.array.place_details);
        tvDetail.setText(details[position % details.length]);

        String[] locations = resources.getStringArray(R.array.place_locations);
        tvLocation.setText(locations[position % locations.length]);

        TypedArray pics = resources.obtainTypedArray(R.array.places_picture);
        ivImage.setImageDrawable(pics.getDrawable(position % pics.length()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
