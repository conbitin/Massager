/*
 * Created by  on 1/5/22 11:44 AM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 1/5/22 11:44 AM
 */

package com.android.vibrate.massager.repository;


import androidx.lifecycle.MutableLiveData;

import com.android.vibrate.massager.engine.Music;
import com.android.vibrate.massager.engine.Pattern;

public interface AppRepository {


    /**
     * Vibration intensity: low.
     * @hide
     */
    public static final float VIBRATION_SPEED_LOW = 2f;

    /**
     * Vibration intensity: medium.
     * @hide
     */
    public static final float VIBRATION_SPEED_MEDIUM = 1f;

    /**
     * Vibration intensity: high.
     * @hide
     */
    public static final float VIBRATION_SPEED_HIGH = 0.5f;

    MutableLiveData<Boolean> startButtonStateData();
    void toggleVibrationState();
    void updateVibrationState(boolean start);
    boolean isVibrationStarted();

    MutableLiveData<Float> vibrationIntensityRateData();
    void changeVibrationIntensityRate(float level);
    float getVibrationIntensityRate();


    MutableLiveData<Float> vibrationPauseRateTimeData();
    void changeVibrationPauseTimeRate(float level);
    float getVibrationPauseTimeRate();


    MutableLiveData<Pattern> selectedPatternData();
    void updateSelectedPattern(Pattern pattern);
    Pattern getSelectedPattern();

    public MutableLiveData<Music> selectedMusicData();
    public void updateSelectedMusic(Music music);
    public Music getSelectedMusic();
}
