package com.exam.android2.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * TabPagerAdapter — provides the three Fragment pages for ViewPager2.
 *
 * Uses FragmentStateAdapter (the ViewPager2 equivalent of the old
 * FragmentStatePagerAdapter shown in course material).
 */
public class TabPagerAdapter extends FragmentStateAdapter {

    private static final int TAB_COUNT = 3;

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Each tab gets a fresh instance of TabFragment with its tab index.
        return TabFragment.newInstance(position + 1);
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
