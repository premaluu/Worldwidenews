package com.example.amitvikram.worldwidenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Browse extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BottomNavigationView mMainnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         mMainnav = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mMainnav = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mMainnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Home:
                        Intent intent1 = new Intent(Browse.this, MainActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.Browse:

                        return true;
                    case R.id.Video:
                        Intent video = new Intent(Browse.this, VideoListActivity.class);
                        startActivity(video);
                        return true;

                    case R.id.Setting:

                        Intent intent3 = new Intent(Browse.this, SettingsActivity.class);
                        startActivity(intent3);
                        return true;

                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainnav.setSelectedItemId(R.id.Browse);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_twittertrends) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Business b1 = new Business();
                    return b1;
                case 1:
                    Entertainment b2 = new Entertainment();
                    return b2;
                case 2:
                    Health b3 = new Health();
                    return b3;
                case 3:
                    Science b4 = new Science();
                    return b4;
                case 4:
                    Sports b5 = new Sports();
                    return b5;
                case 5:
                    Technology b6 = new Technology();
                    return b6;

            }
            Business b1 = new Business();
            return b1;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }
    }
}
