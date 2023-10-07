package com.example.newsapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.newsapi.PagerAdapter;
import com.example.newsapi.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem fhome, fscience, fentertainment, fsports, ftechnology, fhealth;
    Toolbar toolbar;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tab);
        fhome = findViewById(R.id.home);
        fscience = findViewById(R.id.science);
        fentertainment = findViewById(R.id.entertainment);
        fsports = findViewById(R.id.sports);
        ftechnology = findViewById(R.id.tech);
        fhealth = findViewById(R.id.health);



        ViewPager viewPager = findViewById(R.id.fragment);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()); // Use tabLayout.getTabCount()
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

}
