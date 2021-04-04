package com.webscare.livenewsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.webscare.livenewsnow.ModelsClasses.LiveChannelsModel;
import com.webscare.livenewsnow.OtherClasses.AlertDialogClass;
import com.webscare.livenewsnow.fragments.AmericanFragment;
import com.webscare.livenewsnow.fragments.BusinessFragment;
import com.webscare.livenewsnow.fragments.HomeFragment;
import com.webscare.livenewsnow.adapters.ViewPagerAdapter;
import com.webscare.livenewsnow.fragments.LiveFragment;
import com.webscare.livenewsnow.fragments.SearchFragment;
import com.webscare.livenewsnow.fragments.UpdatesFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SmartTabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ImageView btnDrawer;
    Switch switchDt;
    SearchView searchView;
    RelativeLayout btnFilter;
    Boolean checkSearchViewStatus = false, seachStatus = false;
    TextView tvVideoPlayer;
    Animation animation;
    public static String checkFragStatus;
    public static int fragmentsCount;
    public static LinearLayout lootieAnimaationLayout;
    String searchPostKeyword;
    public static LiveChannelsModel liveChannelsModel = new LiveChannelsModel();

    public static AlertDialogClass alertDialogClass = new AlertDialogClass();

    private static Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        initializeView();
    }

    public static Context getContext() {
        return context;
    }

    private void initializeView() {

        drawerLayout=findViewById(R.id.drawer_layout);
        btnDrawer=findViewById(R.id.btn_drawer);
        switchDt = findViewById(R.id.switch_dt);
        searchView = findViewById(R.id.search_view);
        btnFilter = findViewById(R.id.btn_filter_parent);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(9);  // system keep 9 page instances on both sides of the current page in memory  // its save the page in memory so when move back then page not reload otherwise page load again, and the param value in function represent how much pages save in memory
        addTabs(viewPager);
        tabLayout.setViewPager(viewPager);
        lootieAnimaationLayout=findViewById(R.id.lootie_animation_layout);


        viewPagerPageChangeListener();


        btnDrawer.setOnClickListener(this);
        switchDt.setOnClickListener(this);
        btnFilter.setOnClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                SearchFragment searchFragment = new SearchFragment(MainActivity.this,searchPostKeyword);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout_at_viewpager, searchFragment).addToBackStack(null)
                        .commit();

                seachStatus = true;

                checkFragStatus = "searchFragment";



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchPostKeyword = newText;

                return false;
            }
        });

    }

    private void viewPagerPageChangeListener() {

        setSelectedTabTextBold(0);
        setDefaultFragmentStatusCheck();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

                setSelectedTabTextBold(position);
                if (position!=0) {
                    setUnselectedTabTextNormal(position-1);
                }
                if (position!=fragmentsCount-1)
                {
                    setUnselectedTabTextNormal(position+1);
                }

                switch(position)
                {
                    case 0:
                        checkFragStatus = "homeFragment";
                        break;
                    case 1:
                        checkFragStatus = "americanFragment";
                        break;
                    case 2:
                        checkFragStatus = "updatesFragment";
                        break;
                    case 3:
                        checkFragStatus = "businessFragment";
                        break;

                }
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDefaultFragmentStatusCheck() {
        checkFragStatus = "homeFragment";
    }

    private void setUnselectedTabTextNormal(int position) {
        TextView tabTextView2 = (TextView) tabLayout.getTabAt(position);
        tabTextView2.setTypeface(null, Typeface.NORMAL); //if you want to set text style normal them keep the first parameter null , otherwise its will not be converted
    }

    private void setSelectedTabTextBold(int position) {
        TextView tabTextView = (TextView) tabLayout.getTabAt(position);
        tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        adapter.addFrag(new HomeFragment(), "HOME");
        adapter.addFrag(new AmericanFragment(), "HEALTH");
        adapter.addFrag(new BusinessFragment(), "BUSINESS");
        adapter.addFrag(new LiveFragment(MainActivity.this), "LIVE");
//        adapter.addFrag(new AmericanFragment(), "DAILY");
//        adapter.addFrag(new AmericanFragment(), "MAGAZINE");
//        adapter.addFrag(new AmericanFragment(), "SPORTS");
//        adapter.addFrag(new AmericanFragment(), "FINANCE");
//        adapter.addFrag(new AmericanFragment(), "POLITICS");

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

                //apply animation
                animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.move_right);

                searchView.setAnimation(animation);

                if (checkSearchViewStatus)
                {
                    searchView.setVisibility(View.GONE);
                    checkSearchViewStatus = false;
                }
                else {
                    searchView.setVisibility(View.VISIBLE);
//                    searchView.scheduleLayoutAnimation();
                    checkSearchViewStatus = true;

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
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (seachStatus == true)
//
        {
            getSupportFragmentManager().popBackStack();
//            getFragmentManager().popBackStack();
//            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
//            adapter.getItem(1);
//            viewPager.setCurrentItem(0, true);
        }

//        if (viewPager.getCurrentItem() == 1 || viewPager.getCurrentItem() == 2 || viewPager.getCurrentItem() == 3) {

//            if (getFragmentManager().getBackStackEntryCount() != 0) {
//                getFragmentManager().popBackStack();
//            }

//            viewPager.setCurrentItem(0, true);
//        } else {
//            super.onBackPressed(); // This will pop the Activity from the stack.
//        }


//        if (getFragmentManager().getBackStackEntryCount() != 0) {
//            getFragmentManager().popBackStack();
//        }
    }

    public static void animationShow()
    {

        lootieAnimaationLayout.setVisibility(View.VISIBLE);
    }
    public static void animationHide()
    {
        lootieAnimaationLayout.setVisibility(View.GONE);
    }
}