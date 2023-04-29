/*
 * Copyright (c) 2023
 * Create by  on 4/24/23, 3:15 PM
 * Last modified 4/24/23, 3:15 PM
 */

package com.android.vibrate.massager.engine;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.App;

import java.util.ArrayList;

public class MusicProvider {
    private static final String TAG = MusicProvider.class.getSimpleName();
    private static MusicProvider sInstance;

    private ArrayList<Music> mMusics = new ArrayList<>();


    private MusicProvider() {
        prepareList();
    }

    public static MusicProvider getInstance() {
        if (sInstance == null) {
            synchronized (MusicProvider.class) {
                if (sInstance == null) {
                    sInstance = new MusicProvider();
                }
            }
        }
        return sInstance;
    }

    public ArrayList<Music> getMusics() {
        return mMusics;
    }

    public void prepareList() {
        mMusics.add(Music.create(0, App.self().getString(R.string.no_music)));
        mMusics.add(Music.create(R.raw.birds, "Birds"));
        mMusics.add(Music.create(R.raw.fire, "Fire"));
        mMusics.add(Music.create(R.raw.rain, "Rain"));
        mMusics.add(Music.create(R.raw.rain2, "Rain2"));
        mMusics.add(Music.create(R.raw.sea, "Sea"));
    }
}
