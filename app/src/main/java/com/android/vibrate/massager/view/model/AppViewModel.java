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
    public AppViewModel(final AppRepository repository) {
        this.mRepository = repository;
    }

    public MutableLiveData<Boolean> startButtonState() { return this.mRepository.startButtonStateData(); }
    public void toggleVibrationState() {
        this.mRepository.toggleVibrationState(); }
    public void updateVibrationState(final boolean start) {
        this.mRepository.updateVibrationState(start); }
    public boolean isVibrationStarted() {
        return this.mRepository.isVibrationStarted();
    }

    public MutableLiveData<Float> vibrationIntensityRateData() { return this.mRepository.vibrationIntensityRateData(); }
    public void changeVibrationIntensityRate(final float level) {
        this.mRepository.changeVibrationIntensityRate(level); }
    public float getVibrationIntensityRate() { return this.mRepository.getVibrationIntensityRate(); }

    public MutableLiveData<Float> vibrationPauseTimeRateData() { return this.mRepository.vibrationPauseRateTimeData(); }
    public void changeVibrationPauseRateTime(final float level) {
        this.mRepository.changeVibrationPauseTimeRate(level); }
    public float getVibrationPauseTimeRate() { return this.mRepository.getVibrationPauseTimeRate(); }


    public MutableLiveData<Pattern> selectedPatternData() { return this.mRepository.selectedPatternData(); }
    public void updateSelectedPattern(final Pattern pattern) {
        this.mRepository.updateSelectedPattern(pattern); }
    public Pattern getSelectedPattern() { return this.mRepository.getSelectedPattern(); }

    public MutableLiveData<Music> selectedMusicData() { return this.mRepository.selectedMusicData(); }
    public void updateSelectedMusic(final Music music) {
        this.mRepository.updateSelectedMusic(music); }
    public Music getSelectedMusic() { return this.mRepository.getSelectedMusic(); }
}

