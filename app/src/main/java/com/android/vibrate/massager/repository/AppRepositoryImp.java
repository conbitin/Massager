/*
 * Created by  on 1/5/22 11:41 AM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22 11:33 AM
 */

package com.android.vibrate.massager.repository;


import androidx.lifecycle.MutableLiveData;

import com.android.vibrate.massager.engine.Music;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.engine.PatternProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppRepositoryImp implements AppRepository {
    public static final String TAG = "AppRepositoryImp";

    private final MutableLiveData<Boolean> startButtonStateLiveData;

    private final MutableLiveData<Float> vibrationIntensityLiveData;

    private final MutableLiveData<Float> vibrationPauseTimeLiveData;

    private final MutableLiveData<Pattern> selectedPatternData;

    private final MutableLiveData<Music> selectedMusicData;


    @Inject
    public AppRepositoryImp() {
        this.startButtonStateLiveData = new MutableLiveData<>();
        this.startButtonStateLiveData.setValue(false);

        this.vibrationIntensityLiveData = new MutableLiveData<>();
        this.vibrationIntensityLiveData.setValue(1f);

        this.vibrationPauseTimeLiveData = new MutableLiveData<>();
        this.vibrationPauseTimeLiveData.setValue(AppRepository.VIBRATION_SPEED_MEDIUM);

        this.selectedPatternData = new MutableLiveData<>();
        this.selectedPatternData.setValue(PatternProvider.getInstance().getDefaultPattern());

        this.selectedMusicData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<Boolean> startButtonStateData() {
        return this.startButtonStateLiveData;
    }

    @Override
    public void toggleVibrationState() {
        this.startButtonStateLiveData.postValue(!this.isVibrationStarted());
    }

    @Override
    public void updateVibrationState(final boolean start) {
        this.startButtonStateLiveData.postValue(start);
    }

    @Override
    public boolean isVibrationStarted() {
        return this.startButtonStateLiveData.getValue();
    }

    @Override
    public MutableLiveData<Float> vibrationIntensityRateData() {
        return this.vibrationIntensityLiveData;
    }

    @Override
    public void changeVibrationIntensityRate(final float level) {
        this.vibrationIntensityLiveData.postValue(level);
    }

    @Override
    public float getVibrationIntensityRate() {
        return this.vibrationIntensityLiveData.getValue();
    }

    @Override
    public MutableLiveData<Float> vibrationPauseRateTimeData() {
        return this.vibrationPauseTimeLiveData;
    }

    @Override
    public void changeVibrationPauseTimeRate(final float level) {
        this.vibrationPauseTimeLiveData.postValue(level);
    }

    @Override
    public float getVibrationPauseTimeRate() {
        return this.vibrationPauseTimeLiveData.getValue();
    }

    @Override
    public MutableLiveData<Pattern> selectedPatternData() {
        return this.selectedPatternData;
    }

    @Override
    public void updateSelectedPattern(final Pattern pattern) {
        this.selectedPatternData.postValue(pattern);
    }

    @Override
    public Pattern getSelectedPattern() {
        return this.selectedPatternData.getValue();
    }

    @Override
    public MutableLiveData<Music> selectedMusicData() {
        return this.selectedMusicData;
    }

    @Override
    public void updateSelectedMusic(final Music music) {
        this.selectedMusicData.postValue(music);
    }

    @Override
    public Music getSelectedMusic() {
        return this.selectedMusicData.getValue();
    }


}
