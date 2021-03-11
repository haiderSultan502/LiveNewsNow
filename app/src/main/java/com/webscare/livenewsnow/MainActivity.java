package com.webscare.livenewsnow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.material.tabs.TabLayout;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.webscare.livenewsnow.fragments.AmericanFragment;
import com.webscare.livenewsnow.fragments.HomeFragment;
import com.webscare.livenewsnow.adapters.ViewPagerAdapter;
import com.webscare.livenewsnow.fragments.VideoPlayerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SmartTabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ImageView btnDrawer;
    Switch switchDt;
    SearchView searchView;
    RelativeLayout btnFilter;
    Boolean checkSearchStatus = false;
    TextView tvVideoPlayer;
    public static String checkFragStatus;

    VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    private void initializeView() {

        drawerLayout=findViewById(R.id.drawer_layout);
        btnDrawer=findViewById(R.id.btn_drawer);
        switchDt = findViewById(R.id.switch_dt);
        searchView = findViewById(R.id.search_view);
        btnFilter = findViewById(R.id.btn_filter_parent);
        tvVideoPlayer = findViewById(R.id.tv_video_player);

        viewPager = findViewById(R.id.view_pager);
        addTabs(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setViewPager(viewPager);

        btnDrawer.setOnClickListener(this);
        switchDt.setOnClickListener(this);
        btnFilter.setOnClickListener(this);
        tvVideoPlayer.setOnClickListener(this);



    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        adapter.addFrag(new HomeFragment(), "HOME");
        adapter.addFrag(new AmericanFragment(), "AMERICAN");
        adapter.addFrag(new AmericanFragment(), "UPDATES");
        adapter.addFrag(new AmericanFragment(), "BUSINESS");
        adapter.addFrag(new AmericanFragment(), "DAILY");
        adapter.addFrag(new AmericanFragment(), "MAGAZINE");
        adapter.addFrag(new AmericanFragment(), "SPORTS");
        adapter.addFrag(new AmericanFragment(), "FINANCE");
        adapter.addFrag(new AmericanFragment(), "POLITICS");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_drawer:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.btn_filter_parent:
                if (checkSearchStatus)
                {
                    searchView.setVisibility(View.GONE);
                    checkSearchStatus = false;
                }
                else {
                    searchView.setVisibility(View.VISIBLE);
                    searchView.scheduleLayoutAnimation();
                    checkSearchStatus = true;
                }
                break;
            case R.id.switch_dt:

                if (switchDt.isChecked())
                {
                    switchDt.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else
                {
                    switchDt.setChecked(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                break;
            case R.id.tv_video_player:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout_parent, videoPlayerFragment).addToBackStack(null)
                        .commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        }
    }
}