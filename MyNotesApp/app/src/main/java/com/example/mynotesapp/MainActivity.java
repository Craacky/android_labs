package com.example.mynotesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mynotesapp.adapters.ViewPagerAdapter;
import com.example.mynotesapp.fragments.FragmentAdd;
import com.example.mynotesapp.fragments.FragmentDel;
import com.example.mynotesapp.fragments.FragmentShow;
import com.example.mynotesapp.fragments.FragmentUpdate;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new FragmentShow(), "Show");
        adapter.addFragment(new FragmentAdd(), "Add");
        adapter.addFragment(new FragmentDel(), "Del");
        adapter.addFragment(new FragmentUpdate(), "Update");

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Show"); break;
                case 1: tab.setText("Add"); break;
                case 2: tab.setText("Del"); break;
                case 3: tab.setText("Update"); break;
            }
        }).attach();
    }

    public void refreshShowFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f0");
        if (fragment instanceof FragmentShow) {
            ((FragmentShow) fragment).refreshNotes();
        }
    }
}
