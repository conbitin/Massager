/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 10:53 AM
 * Last modified 4/21/23, 10:17 AM
 */

package com.android.vibrate.massager.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.vibrate.massager.core.App;
import com.android.vibrate.massager.core.AppObservable;
import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.databinding.ActivityMainBinding;
import com.android.vibrate.massager.engine.MusicEngine;
import com.android.vibrate.massager.engine.PatternProvider;
import com.android.vibrate.massager.engine.VibrationEngine;
import com.android.vibrate.massager.view.custom.ViewUtils;
import com.android.vibrate.massager.view.fragments.IntensityFragment;
import com.android.vibrate.massager.view.fragments.PatternFragment;
import com.android.vibrate.massager.view.fragments.SettingsFragment;
import com.android.vibrate.massager.view.model.AppViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

public class MainActivity extends FragmentActivity implements Observer {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;
    private TabLayoutMediator mTabLayoutMediator;
    private PatternFragment mPatternFragment;

    @Inject
    AppViewModel mAppViewModel;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.self().getAppComponent().inject(this);
        AppObservable.getInstance().addObserver(this);

        Log.i(MainActivity.TAG, "onCreate");
        this.mBinding = ActivityMainBinding.inflate(this.getLayoutInflater());
        this.setContentView(this.mBinding.getRoot());
        this.mBinding.viewPager.setOffscreenPageLimit(MainActivity.PAGE_COUNT);
        this.mBinding.viewPager.setUserInputEnabled(false); //prevent swipe
        this.initFragments();
        this.mBinding.tabSettings.setOnClickListener(view -> {
            this.handleTabClick(MainActivity.POS_SETTINGS_PAGE); });
        this.mBinding.tabIntensity.setOnClickListener(view -> {
            this.handleTabClick(MainActivity.POS_INTENSITY_PAGE); });
        this.mBinding.tabPattern.setOnClickListener(view -> {
            this.handleTabClick(MainActivity.POS_PATTERN); });


    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewUtils.setAlphaExit(this.mBinding.splashView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MainActivity.TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MainActivity.TAG, "onResume");
    }

    @Override
    protected void onPause() {
        Log.i(MainActivity.TAG, "onPause");
        super.onPause();
    }


    @Override
    protected void onStop() {
        Log.i(MainActivity.TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(MainActivity.TAG, "onDestroy");
        AppObservable.getInstance().deleteObserver(this);
        this.mBinding = null;
        super.onDestroy();
    }

    @Override
    public void update(final Observable observable, final Object data) {
        if (data == null || !(data instanceof Event)) return;
        final Event event = (Event) data;
        switch (event.getEventCode()) {
            case Event.EVENT_TOGGLE_START_VIBRATION:
                VibrationEngine.getInstance().vibrateUpdate(this.mAppViewModel.getSelectedPattern(), this.mAppViewModel.getVibrationPauseTimeRate(), this.mAppViewModel.getVibrationIntensityRate(), !this.mAppViewModel.isVibrationStarted());
                MusicEngine.getInstance().musicUpdate(this.mAppViewModel.getSelectedMusic(), !this.mAppViewModel.isVibrationStarted());
                this.mAppViewModel.toggleVibrationState();
                break;

            case Event.EVENT_SHOW_SHARE_THE_APP:
                ActionHandler.share(this);
                break;

            case Event.EVENT_SHOW_RATE_THE_APP:
                ActionHandler.rateApp(this);
                break;

            case Event.EVENT_SHOW_SEND_FEEDBACK:
                ActionHandler.sendFeedback(this);
                break;

            case Event.EVENT_SHOW_UNLOCK_PATTERN:
                ActionHandler.unlockVibration(this, event.getTextValue());
                break;

            case Event.EVENT_SHOW_UNLOCK_EVERYTHING:
                ActionHandler.unlockEverything(this);
                break;

            case Event.EVENT_SHOW_MUSIC:
                ActionHandler.musicChooser(this);
                break;

            case Event.EVENT_SHOW_NOT_VIBRATION:
                ActionHandler.noVibration(this);
                break;

            case Event.EVENT_SHOW_CREATE_VIBRATION:
                ActionHandler.createVibration(this);
                break;

        }
    }


    private void initFragments() {
        this.mPatternFragment = new PatternFragment();
        this.mBinding.viewPager.setAdapter(new PagerAdapter(this));
        this.mTabLayoutMediator = new TabLayoutMediator(this.mBinding.tabLayout, this.mBinding.viewPager, true, (tab, position) -> {
            switch (position) {
                case MainActivity.POS_SETTINGS_PAGE:
                    tab.setText("Settings");
                    break;

                case MainActivity.POS_INTENSITY_PAGE:
                    tab.setText("Intensity");
                    break;
            }

        });
        this.handleTabClick(MainActivity.POS_INTENSITY_PAGE);

    }

    public static final int POS_SETTINGS_PAGE = 0;
    public static final int POS_INTENSITY_PAGE = 1;
    private static final int PAGE_COUNT = 2; //three fragments
    public static final int POS_PATTERN = 2; // not a page

    private class PagerAdapter extends FragmentStateAdapter {

        public PagerAdapter(@NonNull final FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(final int position) {
            switch (position) {
                case MainActivity.POS_SETTINGS_PAGE:
                    return new SettingsFragment();

                case MainActivity.POS_INTENSITY_PAGE:
                    return new IntensityFragment();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return MainActivity.PAGE_COUNT;
        }
    }

    private void handleTabClick(final int pos) {
        this.mBinding.appTabIndicator.setTabSelected(pos);
        this.mBinding.appTabView.setTabSelected(pos);
        if (pos != MainActivity.POS_PATTERN) {
            ViewUtils.hideFragment(this, this.mPatternFragment);
            this.mBinding.viewPager.setCurrentItem(pos);
        } else {
            ViewUtils.showFragment(this, this.mPatternFragment, this.mBinding.containerFragment.getId());
        }
    }

}
