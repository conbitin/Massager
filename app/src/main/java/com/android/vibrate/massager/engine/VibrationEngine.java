/*
 * Copyright (c) 2023
 * Create by  on 4/23/23, 2:00 PM
 * Last modified 4/23/23, 2:00 PM
 */

package com.android.vibrate.massager.engine;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.android.vibrate.massager.core.App;

public class VibrationEngine {
    private static final String TAG = VibrationEngine.class.getSimpleName();
    private static VibrationEngine sInstance;
    private Vibrator mVibrator;

    private AudioAttributes mAudioAttributes;

    private VibrationEngine() {
        mVibrator = App.self().getSystemService(Vibrator.class);
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setUsage(AudioAttributes.USAGE_UNKNOWN);
        mAudioAttributes = builder.build();
    }

    public static VibrationEngine getInstance() {
        if (sInstance == null) {
            synchronized (VibrationEngine.class) {
                if (sInstance == null) {
                    sInstance = new VibrationEngine();
                }
            }
        }

        return sInstance;
    }

    public void playPatternNoRepeat(long [] pattern) {
        stop();
        if (pattern != null && pattern.length > 0) {
            mVibrator.vibrate(pattern, -1);
        }
    }

    public void playPattern(long [] pattern) {
        stop();
        if (pattern != null && pattern.length > 0) {
            mVibrator.vibrate(pattern, 1);
        }
    }

    private void play(Pattern pattern, float pauseTimeRate, float intensityRate) {
        playPattern(remakePatternWaveform(pattern, pauseTimeRate, intensityRate));
    }

    private long[] remakePatternWaveform(Pattern pattern, float intervalRate, float intensityRate) {
        if (pattern == null || pattern.pattern == null) return new long[0];
        long[] waveForm = new long[pattern.pattern.length];
        for (int i = 0; i < waveForm.length; i ++) {
            waveForm[i] = (long) (pattern.pattern[i] * (i %2 ==0 ? intervalRate : intensityRate));
        }
        return waveForm;
    }

    public void stop() {
        mVibrator.cancel();
    }

    public void vibrateUpdate(Pattern pattern, float pauseTimeRate, float intensityRate, boolean play) {
        stop();
        if (play) {
            play(pattern, pauseTimeRate, intensityRate);
        }
    }

    public void vibrate() {
        stop();
        mVibrator.vibrate(100000);
    }

}
