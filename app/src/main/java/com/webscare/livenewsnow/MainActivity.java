package com.webscare.livenewsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.webscare.livenewsnow.fragments.AmericanFragment;
import com.webscare.livenewsnow.fragments.HomeFragment;
import com.webscare.livenewsnow.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    private void initializeView() {

        viewPager = findViewById(R.id.view_pager);
        addTabs(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new AmericanFragment(), "American");
        adapter.addFrag(new HomeFragment(), "Updates");
        adapter.addFrag(new HomeFragment(), "Business");
//        adapter.addFrag(new HomeFragment(), "Daily");
//        adapter.addFrag(new HomeFragment(), "Magazine");
//        adapter.addFrag(new HomeFragment(), "Sports");
//        adapter.addFrag(new HomeFragment(), "Finance");
//        adapter.addFrag(new HomeFragment(), "Politics");

        viewPager.setAdapter(adapter);
    }
}