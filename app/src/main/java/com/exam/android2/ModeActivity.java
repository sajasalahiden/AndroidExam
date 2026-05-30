package com.exam.android2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.exam.android2.ui.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
public class ModeActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout  tabLayout;
    private Button     btnSetMode;

    private static final int TAB_FIRST  = 0;
    private static final int TAB_SECOND = 1;
    private static final int TAB_THIRD  = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.mode_activity_title);
        }

        viewPager2 = findViewById(R.id.view_pager);
        tabLayout  = findViewById(R.id.tab_layout);
        btnSetMode = findViewById(R.id.btn_set_mode);

        TabPagerAdapter adapter = new TabPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case TAB_FIRST:  tab.setText(R.string.tab_first);  break;
                case TAB_SECOND: tab.setText(R.string.tab_second); break;
                case TAB_THIRD:  tab.setText(R.string.tab_third);  break;
            }
        }).attach();

        btnSetMode.setOnClickListener(this::showSetModePopup);
    }



    @Override
    public void onBackPressed() {
        int currentTab = viewPager2.getCurrentItem();

        if (currentTab == TAB_SECOND || currentTab == TAB_THIRD) {
            // Go back to the first tab instead of leaving the activity.
            viewPager2.setCurrentItem(TAB_FIRST, true /* smoothScroll */);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showSetModePopup(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_set_mode, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.mode_light) {
                applyAppMode(AppMode.LIGHT);
                return true;
            } else if (id == R.id.mode_night) {
                applyAppMode(AppMode.NIGHT);
                return true;
            } else if (id == R.id.mode_system) {
                applyAppMode(AppMode.SYSTEM);
                return true;
            }
            return false;
        });

        popup.show();
    }


    private void applyAppMode(AppMode mode) {
        int nightMode;
        switch (mode) {
            case LIGHT:
                nightMode = androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
                break;
            case NIGHT:
                nightMode = androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;
                break;
            default: // SYSTEM
                nightMode = androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                break;
        }
        androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode(nightMode);
    }


    private enum AppMode { LIGHT, NIGHT, SYSTEM }
}
