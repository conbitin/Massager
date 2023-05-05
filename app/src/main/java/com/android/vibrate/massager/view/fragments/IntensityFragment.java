/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 11:19 AM
 * Last modified 4/21/23, 11:19 AM
 */

package com.android.vibrate.massager.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.databinding.FragmentIntensityBinding;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.engine.VibrationEngine;
import com.android.vibrate.massager.view.custom.ViewUtils;

import static com.android.vibrate.massager.repository.AppRepository.VIBRATION_SPEED_HIGH;
import static com.android.vibrate.massager.repository.AppRepository.VIBRATION_SPEED_LOW;
import static com.android.vibrate.massager.repository.AppRepository.VIBRATION_SPEED_MEDIUM;

public class IntensityFragment extends BaseFragment {

    private FragmentIntensityBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.mBinding = FragmentIntensityBinding.inflate(inflater, container, false);

        this.mBinding.unlockButton.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_UNLOCK_PATTERN);});
        this.mBinding.notVibration.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_NOT_VIBRATION);});
        this.mBinding.startButton.setOnClickListener(view  -> {
            this.sendEvent(Event.EVENT_TOGGLE_START_VIBRATION);});
        this.mBinding.musicButton.setOnClickListener(view  -> {
            this.sendEvent(Event.EVENT_SHOW_MUSIC);});
        this.mBinding.speedLow.setOnClickListener(view  -> {
            this.mAppViewModel.changeVibrationPauseRateTime(VIBRATION_SPEED_LOW);});
        this.mBinding.speedMedium.setOnClickListener(view  -> {
            this.mAppViewModel.changeVibrationPauseRateTime(VIBRATION_SPEED_MEDIUM);});
        this.mBinding.speedFast.setOnClickListener(view  -> {
            this.mAppViewModel.changeVibrationPauseRateTime(VIBRATION_SPEED_HIGH);});
        this.mBinding.seekBarVibration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int level, final boolean fromUser) {
                if (fromUser) IntensityFragment.this.mAppViewModel.changeVibrationIntensityRate(level * 1f / 10f);
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {}
        });

        this.mAppViewModel.startButtonState().observe(this.getViewLifecycleOwner(), start -> {
            this.mBinding.startButton.setActivated(start);
            this.mBinding.animationButton.setVisibility(start ? View.VISIBLE : View.GONE);
            this.mBinding.guideToStart.setVisibility(start ? View.GONE : View.VISIBLE);
            this.mBinding.modeInfo.setVisibility(start ? View.VISIBLE : View.GONE);
            ViewUtils.runAnimationDrawable(start, this.mBinding.animationButton);
        });

        this.mAppViewModel.vibrationIntensityRateData().observe(this.getViewLifecycleOwner(), intensityRate -> {
            this.mBinding.seekBarVibration.setProgress((int)(intensityRate * 10f + 0.1));
            VibrationEngine.getInstance().vibrateUpdate(this.mAppViewModel.getSelectedPattern(), this.mAppViewModel.getVibrationPauseTimeRate(), this.mAppViewModel.getVibrationIntensityRate(), this.mAppViewModel.isVibrationStarted());
        });

        this.mAppViewModel.vibrationPauseTimeRateData().observe(this.getViewLifecycleOwner(), level -> {
            this.mBinding.speedLow.setActivated(level == VIBRATION_SPEED_LOW);
            this.mBinding.speedMedium.setActivated(level == VIBRATION_SPEED_MEDIUM);
            this.mBinding.speedFast.setActivated(level == VIBRATION_SPEED_HIGH);
            VibrationEngine.getInstance().vibrateUpdate(this.mAppViewModel.getSelectedPattern(), this.mAppViewModel.getVibrationPauseTimeRate(), this.mAppViewModel.getVibrationIntensityRate(), this.mAppViewModel.isVibrationStarted());
        });

        this.mAppViewModel.selectedPatternData().observe(this.getViewLifecycleOwner(), pattern -> {
            if (pattern != null) {
                this.mBinding.modeIcon.setImageResource(pattern.pic);
                if (pattern.type == Pattern.TYPE_NORMAL) {
                    this.mBinding.modeName.setText(pattern.title);
                } else if (pattern.type == Pattern.TYPE_CUSTOM) {
                    this.mBinding.modeName.setText(pattern.title);
                } else if (pattern.type == Pattern.TYPE_CREATE) {
                    this.mBinding.modeName.setText(this.getString(R.string.undefined));
                } else if (pattern.type == Pattern.TYPE_LOCK) {
                    this.mBinding.modeName.setText(this.getString(R.string.undefined));
                }
            } else {
                this.mBinding.modeName.setText(this.getString(R.string.undefined));
            }
        });

        return this.mBinding.getRoot();
    }


    @Override
    public void onStop() {
        this.sendEvent(Event.EVENT_STOP_VIBRATION_WHEN_EXIT);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mBinding = null;
    }


}
