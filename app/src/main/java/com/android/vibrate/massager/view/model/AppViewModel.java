/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 6:45 PM
 * Last modified 4/21/23, 6:45 PM
 */

package com.android.vibrate.massager.view.model;

import androidx.lifecycle.MutableLiveData;

import com.android.vibrate.massager.engine.Music;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.repository.AppRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppViewModel {
    AppRepository mRepository;

    @Inject
    public AppViewModel(AppRepository repository) {
        mRepository = repository;
    }

    public MutableLiveData<Boolean> startButtonState() { return mRepository.startButtonStateData(); }
    public void toggleVibrationState() { mRepository.toggleVibrationState(); }
    public void updateVibrationState(boolean start) { mRepository.updateVibrationState(start); }
    public boolean isVibrationStarted() {
        return mRepository.isVibrationStarted();
    }

    public MutableLiveData<Float> vibrationIntensityRateData() { return mRepository.vibrationIntensityRateData(); }
    public void changeVibrationIntensityRate(float level) { mRepository.changeVibrationIntensityRate(level); }
    public float getVibrationIntensityRate() { return mRepository.getVibrationIntensityRate(); }

    public MutableLiveData<Float> vibrationPauseTimeRateData() { return mRepository.vibrationPauseRateTimeData(); }
    public void changeVibrationPauseRateTime(float level) { mRepository.changeVibrationPauseTimeRate(level); }
    public float getVibrationPauseTimeRate() { return mRepository.getVibrationPauseTimeRate(); }


    public MutableLiveData<Pattern> selectedPatternData() { return mRepository.selectedPatternData(); }
    public void updateSelectedPattern(Pattern pattern) { mRepository.updateSelectedPattern(pattern); }
    public Pattern getSelectedPattern() { return  mRepository.getSelectedPattern(); }

    public MutableLiveData<Music> selectedMusicData() { return mRepository.selectedMusicData(); }
    public void updateSelectedMusic(Music music) { mRepository.updateSelectedMusic(music); }
    public Music getSelectedMusic() { return  mRepository.getSelectedMusic(); }
}

