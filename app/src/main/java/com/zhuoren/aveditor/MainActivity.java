package com.zhuoren.aveditor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhuoren.aveditor.edit.video.VideoFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    private Fragment mVideoFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar mBottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.video, "video"))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);

        if (savedInstanceState != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mVideoFragment = fragmentManager.findFragmentByTag(VideoFragment.TAG);
        }

        if (mVideoFragment == null) {
            mVideoFragment = VideoFragment.newInstance();
        }


        changeFragment(0);
    }


    @Override
    public void onTabSelected(int position) {
        changeFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void changeFragment(int position) {
        ActionBar bar = getSupportActionBar();
        switch (position) {
            case 0:
            default:
                switchFragment(mVideoFragment, VideoFragment.TAG);
                bar.setTitle(VideoFragment.TITLE);
                break;
        }
    }


    private void switchFragment(Fragment targetFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.add(R.id.frameLayout, targetFragment, tag).commit();
        } else {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.show(targetFragment).commit();
        }
        mCurrentFragment = targetFragment;
    }

}
