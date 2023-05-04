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
    private final Vibrator mVibrator;

    private final AudioAttributes mAudioAttributes;

    private VibrationEngine() {
        this.mVibrator = App.self().getSystemService(Vibrator.class);
        final AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setUsage(AudioAttributes.USAGE_UNKNOWN);
        this.mAudioAttributes = builder.build();
    }

    public static VibrationEngine getInstance() {
        if (VibrationEngine.sInstance == null) {
            synchronized (VibrationEngine.class) {
                if (VibrationEngine.sInstance == null) {
                    VibrationEngine.sInstance = new VibrationEngine();
                }
            }
        }

        return VibrationEngine.sInstance;
    }

    public void playPatternNoRepeat(final long [] pattern) {
        this.stop();
        if (pattern != null && pattern.length > 0) {
            this.mVibrator.vibrate(pattern, -1);
        }
    }

    public void playPattern(final long [] pattern) {
        this.stop();
        if (pattern != null && pattern.length > 0) {
            this.mVibrator.vibrate(pattern, 1);
        }
    }

    private void play(final Pattern pattern, final float pauseTimeRate, final float intensityRate) {
        this.playPattern(this.remakePatternWaveform(pattern, pauseTimeRate, intensityRate));
    }

    private long[] remakePatternWaveform(final Pattern pattern, final float intervalRate, final float intensityRate) {
        if (pattern == null || pattern.pattern == null) return new long[0];
        final long[] waveForm = new long[pattern.pattern.length];
        for (int i = 0; i < waveForm.length; i ++) {
            waveForm[i] = (long) (pattern.pattern[i] * (i %2 ==0 ? intervalRate : intensityRate));
        }
        return waveForm;
    }

    public void stop() {
        this.mVibrator.cancel();
    }

    public void vibrateUpdate(final Pattern pattern, final float pauseTimeRate, final float intensityRate, final boolean play) {
        this.stop();
        if (play) {
            this.play(pattern, pauseTimeRate, intensityRate);
        }
    }

    public void vibrate() {
        this.stop();
        this.mVibrator.vibrate(100000);
    }

}
