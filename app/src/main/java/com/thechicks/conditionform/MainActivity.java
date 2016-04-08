package com.thechicks.conditionform;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ActionBarDrawerToggle mActionBarDrawerToggle;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupDrawerContent(mNavigationView);

        //Animate the Hamburger Icon
        mActionBarDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        fm = getSupportFragmentManager();

        //초기 화면 지정
        Fragment fragmnet = fm.findFragmentById(R.id.container_fragment);
        if (fragmnet == null) {
            HomeFragment homeFragment = HomeFragment.newInstance();

            fm.beginTransaction()
                    .add(R.id.container_fragment, homeFragment)
                    .commit();
        }
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.getMenu().getItem(0).setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    public void selectDrawerItem(MenuItem menuItem) {

        //체크되어 있다면 현재화면이므로 바꿀 필요없다.
        if (menuItem.isChecked()) {
            mDrawerLayout.closeDrawers();
            return;
        }

        Fragment fragment = fm.findFragmentById(R.id.container_fragment);
        Fragment newFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_home_fragment:
                if (fragment instanceof HomeFragment) {
                    return;
                }
                newFragment = HomeFragment.newInstance();
                break;
            case R.id.nav_history_fragment:
                if (fragment instanceof HistoryFragment) {
                    return;
                }
                newFragment = HistoryFragment.newInstance();
                break;
            case R.id.nav_statistics_fragment:
                if (fragment instanceof StatisticsFragment) {
                    return;
                }
                newFragment = StatisticsFragment.newInstance();
                break;
            case R.id.nav_search_fragment:
                if (fragment instanceof SearchFragment) {
                    return;
                }
                newFragment = SearchFragment.newInstance();
                break;
            case R.id.nav_settings_fragment:
                if (fragment instanceof SettingsFragment) {
                    return;
                }
                newFragment = SettingsFragment.newInstance();
                break;
        }

        if (newFragment != null) {
            // Insert the fragment by replacing any existing fragment
            fm.beginTransaction().replace(R.id.container_fragment, newFragment).commit();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.d(TAG, " home");
                return true;
//            case R.id.action_settings:
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    //런타임에서 설정 변경시 호출
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggles
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
