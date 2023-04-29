/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 11:19 AM
 * Last modified 4/21/23, 11:19 AM
 */

package com.android.vibrate.massager.view.fragments;

import android.graphics.drawable.Drawable;
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
    private static final String TAG = IntensityFragment.class.getSimpleName();

    private FragmentIntensityBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentIntensityBinding.inflate(inflater, container, false);

        mBinding.unlockButton.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_UNLOCK_PATTERN);});
        mBinding.notVibration.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_NOT_VIBRATION);});
        mBinding.startButton.setOnClickListener(view  -> { sendEvent(Event.EVENT_TOGGLE_START_VIBRATION);});
        mBinding.musicButton.setOnClickListener(view  -> { sendEvent(Event.EVENT_SHOW_MUSIC);});
        mBinding.speedLow.setOnClickListener(view  -> { mAppViewModel.changeVibrationPauseRateTime(VIBRATION_SPEED_LOW);});
        mBinding.speedMedium.setOnClickListener(view  -> { mAppViewModel.changeVibrationPauseRateTime(VIBRATION_SPEED_MEDIUM);});
        mBinding.speedFast.setOnClickListener(view  -> { mAppViewModel.changeVibrationPauseRateTime(VIBRATION_SPEED_HIGH);});
        mBinding.seekBarVibration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int level, boolean fromUser) {
                if (fromUser) mAppViewModel.changeVibrationIntensityRate(level * 1f / 10f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        mAppViewModel.startButtonState().observe(getViewLifecycleOwner(), start -> {
            mBinding.startButton.setActivated(start);
            mBinding.animationButton.setVisibility(start ? View.VISIBLE : View.GONE);
            mBinding.guideToStart.setVisibility(start ? View.GONE : View.VISIBLE);
            mBinding.modeInfo.setVisibility(start ? View.VISIBLE : View.GONE);
            ViewUtils.runAnimationDrawable(start, mBinding.animationButton);
        });

        mAppViewModel.vibrationIntensityRateData().observe(getViewLifecycleOwner(), intensityRate -> {
            mBinding.seekBarVibration.setProgress((int)(intensityRate * 10f + 0.1));
            VibrationEngine.getInstance().vibrateUpdate(mAppViewModel.getSelectedPattern(), mAppViewModel.getVibrationPauseTimeRate(), mAppViewModel.getVibrationIntensityRate(), mAppViewModel.isVibrationStarted());
        });

        mAppViewModel.vibrationPauseTimeRateData().observe(getViewLifecycleOwner(), level -> {
            mBinding.speedLow.setActivated(level == VIBRATION_SPEED_LOW);
            mBinding.speedMedium.setActivated(level == VIBRATION_SPEED_MEDIUM);
            mBinding.speedFast.setActivated(level == VIBRATION_SPEED_HIGH);
            VibrationEngine.getInstance().vibrateUpdate(mAppViewModel.getSelectedPattern(), mAppViewModel.getVibrationPauseTimeRate(), mAppViewModel.getVibrationIntensityRate(), mAppViewModel.isVibrationStarted());
        });

        mAppViewModel.selectedPatternData().observe(getViewLifecycleOwner(), pattern -> {
            if (pattern != null) {
                mBinding.modeIcon.setImageResource(pattern.pic);
                if (pattern.type == Pattern.TYPE_NORMAL) {
                    mBinding.modeName.setText(pattern.title);
                } else if (pattern.type == Pattern.TYPE_CREATE) {
                    mBinding.modeName.setText(getString(R.string.undefined));
                } else if (pattern.type == Pattern.TYPE_LOCK) {
                    mBinding.modeName.setText(getString(R.string.undefined));
                }
            } else {
                mBinding.modeName.setText(getString(R.string.undefined));
            }
        });

        return mBinding.getRoot();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


}
