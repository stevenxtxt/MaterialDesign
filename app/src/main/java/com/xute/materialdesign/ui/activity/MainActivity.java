package com.xute.materialdesign.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.xute.materialdesign.R;
import com.xute.materialdesign.framework.constant.Constant;
import com.xute.materialdesign.framework.utils.Utils;
import com.xute.materialdesign.ui.fragment.CardFragment;
import com.xute.materialdesign.ui.fragment.ListFragment;
import com.xute.materialdesign.ui.fragment.TitleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tb_tool) Toolbar tbTool;
    @BindView(R.id.tl_tab) TabLayout tlTab;
    @BindView(R.id.abl_title) AppBarLayout ablTitle;
    @BindView(R.id.cl_main) CoordinatorLayout clMain;
    @BindView(R.id.vp_pager) ViewPager vpPager;
    @BindView(R.id.nv_navigation) NavigationView nvNavigation;
    @BindView(R.id.dl_drawer) DrawerLayout dlDrawer;
    @BindView(R.id.flb_btn) FloatingActionButton flbBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        setSupportActionBar(tbTool);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        nvNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                dlDrawer.closeDrawers();
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.one:
                        intent = new Intent(MainActivity.this, OneActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.two:
                        intent = new Intent(MainActivity.this, TwoActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.three:
                        intent = new Intent(MainActivity.this, ThreeActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        setupViewPager(vpPager);
        tlTab.setupWithViewPager(vpPager);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, Constant.BAIDU_PUSH_KEY);
    }

    @OnClick(R.id.flb_btn)
    public void onFloatingButtonClick(View view) {
        final Snackbar snackbar = Snackbar.make(view, "This is Snackbar", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            dlDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager vpPager) {
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new CardFragment(), "Card");
        adapter.addFragment(new ListFragment(), "List");
        adapter.addFragment(new TitleFragment(), "Title");
        vpPager.setAdapter(adapter);
    }

    public class MyAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }
    }

}
