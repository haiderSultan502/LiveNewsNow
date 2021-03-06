package com.webscare.livenewsnow.adapters;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.webscare.livenewsnow.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    MainActivity mainActivity = new MainActivity();

    public ViewPagerAdapter(@NonNull FragmentManager fm,int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override //used to display the actual fragments
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override //return the number of fragments to be displayed
    public int getCount() {

        mainActivity.fragmentsCount = fragmentList.size();
        return fragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }




}
