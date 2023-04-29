/*
 * Copyright (c) 2023
 * Create by  on 4/23/23, 2:01 PM
 * Last modified 4/23/23, 2:01 PM
 */

package com.android.vibrate.massager.engine;

import android.media.MediaPlayer;

import com.android.vibrate.massager.core.App;

public class MusicEngine {
    private static final String TAG = MusicEngine.class.getSimpleName();
    private static MusicEngine sInstance;

    private MediaPlayer mMediaPlayer;

    private MusicEngine() {
    }

    public static MusicEngine getInstance() {
        if (sInstance == null) {
            synchronized (MusicEngine.class) {
                if (sInstance == null) {
                    sInstance = new MusicEngine();
                }
            }
        }
        return sInstance;
    }


    public void playMusic(int source) {
        stopMusic();
        mMediaPlayer = MediaPlayer.create(App.self(), source);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    public void stopMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
        mMediaPlayer = null;
    }

}
