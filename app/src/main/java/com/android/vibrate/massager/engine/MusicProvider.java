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

    private final ArrayList<Music> mMusics = new ArrayList<>();


    private MusicProvider() {
        this.prepareList();
    }

    public static MusicProvider getInstance() {
        if (MusicProvider.sInstance == null) {
            synchronized (MusicProvider.class) {
                if (MusicProvider.sInstance == null) {
                    MusicProvider.sInstance = new MusicProvider();
                }
            }
        }
        return MusicProvider.sInstance;
    }

    public ArrayList<Music> getMusics() {
        return this.mMusics;
    }

    public void prepareList() {
        this.mMusics.add(Music.create(0, App.self().getString(R.string.no_music)));
        this.mMusics.add(Music.create(R.raw.birds, "Birds"));
        this.mMusics.add(Music.create(R.raw.fire, "Fire"));
        this.mMusics.add(Music.create(R.raw.rain, "Rain"));
        this.mMusics.add(Music.create(R.raw.rain2, "Rain2"));
        this.mMusics.add(Music.create(R.raw.sea, "Sea"));
    }
}
