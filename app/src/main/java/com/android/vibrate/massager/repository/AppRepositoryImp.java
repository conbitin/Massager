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

    private MutableLiveData<Boolean> startButtonStateLiveData;

    private MutableLiveData<Float> vibrationIntensityLiveData;

    private MutableLiveData<Float> vibrationPauseTimeLiveData;

    private MutableLiveData<Pattern> selectedPatternData;

    private MutableLiveData<Music> selectedMusicData;


    @Inject
    public AppRepositoryImp() {
        startButtonStateLiveData = new MutableLiveData<>();
        startButtonStateLiveData.setValue(false);

        vibrationIntensityLiveData = new MutableLiveData<>();
        vibrationIntensityLiveData.setValue(1f);

        vibrationPauseTimeLiveData = new MutableLiveData<>();
        vibrationPauseTimeLiveData.setValue(VIBRATION_SPEED_MEDIUM);

        selectedPatternData = new MutableLiveData<>();
        selectedPatternData.setValue(PatternProvider.getInstance().getDefaultPattern());

        selectedMusicData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<Boolean> startButtonStateData() {
        return startButtonStateLiveData;
    }

    @Override
    public void toggleVibrationState() {
        startButtonStateLiveData.postValue(!isVibrationStarted());
    }

    @Override
    public void updateVibrationState(boolean start) {
        startButtonStateLiveData.postValue(start);
    }

    @Override
    public boolean isVibrationStarted() {
        return startButtonStateLiveData.getValue();
    }

    @Override
    public MutableLiveData<Float> vibrationIntensityRateData() {
        return vibrationIntensityLiveData;
    }

    @Override
    public void changeVibrationIntensityRate(float level) {
        vibrationIntensityLiveData.postValue(level);
    }

    @Override
    public float getVibrationIntensityRate() {
        return vibrationIntensityLiveData.getValue();
    }

    @Override
    public MutableLiveData<Float> vibrationPauseRateTimeData() {
        return vibrationPauseTimeLiveData;
    }

    @Override
    public void changeVibrationPauseTimeRate(float level) {
        vibrationPauseTimeLiveData.postValue(level);
    }

    @Override
    public float getVibrationPauseTimeRate() {
        return vibrationPauseTimeLiveData.getValue();
    }

    @Override
    public MutableLiveData<Pattern> selectedPatternData() {
        return selectedPatternData;
    }

    @Override
    public void updateSelectedPattern(Pattern pattern) {
        selectedPatternData.postValue(pattern);
    }

    @Override
    public Pattern getSelectedPattern() {
        return selectedPatternData.getValue();
    }

    @Override
    public MutableLiveData<Music> selectedMusicData() {
        return selectedMusicData;
    }

    @Override
    public void updateSelectedMusic(Music music) {
        selectedMusicData.postValue(music);
    }

    @Override
    public Music getSelectedMusic() {
        return selectedMusicData.getValue();
    }


}
