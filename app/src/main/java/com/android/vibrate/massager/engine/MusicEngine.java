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
        if (MusicEngine.sInstance == null) {
            synchronized (MusicEngine.class) {
                if (MusicEngine.sInstance == null) {
                    MusicEngine.sInstance = new MusicEngine();
                }
            }
        }
        return MusicEngine.sInstance;
    }


    public void playMusic(final int source) {
        this.stopMusic();
        this.mMediaPlayer = MediaPlayer.create(App.self(), source);
        this.mMediaPlayer.setLooping(true);
        this.mMediaPlayer.start();
    }

    public void stopMusic() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();
        }
        this.mMediaPlayer = null;
    }

    public void musicUpdate(final Music music, final boolean play) {
        this.stopMusic();
        if (play && music != null && music.resource != 0) {
            this.playMusic(music.resource);
        }
    }

}
