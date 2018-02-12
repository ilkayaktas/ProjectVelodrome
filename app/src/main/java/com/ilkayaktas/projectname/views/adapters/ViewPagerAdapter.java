package com.ilkayaktas.projectname.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ilkayaktas.projectname.views.activities.base.BaseFragment;
import com.ilkayaktas.projectname.views.fragments.another.AnotherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iaktas on 12.09.2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private final String[] categories;
    private FragmentManager fragmentManager;
    private List<BaseFragment> fragmentList;

    public ViewPagerAdapter(FragmentManager fm, String[] categories) {
        super(fm);
        this.fragmentManager = fm;
        this.categories = categories;
        this.fragmentList = new ArrayList<>();

        initiateFragments();

    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.categories = null;
        this.fragmentList = new ArrayList<>();
    }

    private void initiateFragments(){
        for (String category : categories) {
            fragmentList.add(AnotherFragment.newInstance());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(BaseFragment fragment) {
        fragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories[position];
    }

    public void updateVisibleFragment(int position){
        ((AnotherFragment)fragmentList.get(position)).updateFragment();
    }

    public interface OnUpdateFragment{
        void updateFragment();
    }
}
