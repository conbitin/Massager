/*
 * Copyright (c) 2023
 * Create by  on 4/24/23, 3:14 PM
 * Last modified 4/24/23, 3:14 PM
 */

package com.android.vibrate.massager.engine;

import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;

import com.android.vibrate.massager.core.App;

import java.io.IOException;

import static android.media.MediaMetadataRetriever.METADATA_KEY_DURATION;

public class Music {
    public int resource;
    public String title = "";
    public int duration;
    public Music() {}

    public Music(int resource, String title, int duration) {
        this.resource = resource;
        this.title = title;
        this.duration = duration;
    }


    public static Music create(int resource, String title) {
        return new Music(resource, title, getDuration(resource));
    }


    public static int getDuration(int resource) {
        if (resource == 0) return 0;
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            AssetFileDescriptor fd = App.self().getResources().openRawResourceFd(resource);
            if (fd == null) return 0;
            retriever.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            String duration = retriever.extractMetadata(METADATA_KEY_DURATION);
            try {
                fd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            retriever.release();
            return Integer.parseInt(duration);
        } catch (Exception e) {
            return 0;
        }
    }
}
