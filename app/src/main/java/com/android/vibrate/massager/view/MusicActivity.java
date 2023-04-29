/*
 * Copyright (c) 2023
 * Create by  on 4/22/23, 6:35 PM
 * Last modified 4/22/23, 6:35 PM
 */

package com.android.vibrate.massager.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.App;
import com.android.vibrate.massager.core.AppObservable;
import com.android.vibrate.massager.core.ThreadExecutors;
import com.android.vibrate.massager.databinding.ActivityMusicBinding;
import com.android.vibrate.massager.databinding.MusicItemBinding;
import com.android.vibrate.massager.engine.Music;
import com.android.vibrate.massager.engine.MusicEngine;
import com.android.vibrate.massager.engine.MusicProvider;
import com.android.vibrate.massager.view.custom.ViewUtils;
import com.android.vibrate.massager.view.model.AppViewModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

public class MusicActivity extends AppCompatActivity implements Observer {
    ActivityMusicBinding mBinding;

    @Inject
    AppViewModel mAppViewModel;

    MusicAdapter mMusicAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.self().getAppComponent().inject(this);
        mBinding = ActivityMusicBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        AppObservable.getInstance().addObserver(this);
        mBinding.musicList.setAdapter(mMusicAdapter = new MusicAdapter(this, R.layout.music_item, MusicProvider.getInstance().getMusics(), mAppViewModel));
        mBinding.backButton.setOnClickListener(view ->  { onBackPressed(); });
        mBinding.musicDone.setOnClickListener(view -> { finish(); });

        mAppViewModel.selectedMusicData().observe(this, music -> { mMusicAdapter.notifyDataSetChanged();});
    }

    @Override
    public void onBackPressed() {
        mAppViewModel.updateSelectedMusic(null);
        MusicEngine.getInstance().stopMusic();
        super.onBackPressed();
    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    protected void onDestroy() {
        AppObservable.getInstance().deleteObserver(this);
        super.onDestroy();
    }

    private static class MusicAdapter extends ArrayAdapter<Music> {
        AppViewModel viewModel;
        private Music selectedItem;

        public MusicAdapter(@NonNull Context context, int resource, @NonNull List list, AppViewModel viewModel) {
            super(context, resource, list);
            this.viewModel = viewModel;
            selectedItem = viewModel.getSelectedMusic();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MusicItemBinding binding = null;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.music_item, viewGroup, false);
            }
            Music music = getItem(i);
            binding = MusicItemBinding.bind(view);
            boolean isSelected = isMusicSelected(music);
            binding.getRoot().setActivated(isSelected);
            binding.imgChecked.setVisibility(isSelected ? View.VISIBLE :View.GONE);
            binding.imgPlay.setActivated(isSelected);
            binding.imgPlay.setVisibility(music.resource != 0 ? View.VISIBLE :View.GONE);
            binding.itemRoot.setActivated(isSelected);
            binding.imgMusicAnimation.setVisibility(isSelected ? View.VISIBLE :View.GONE);
            ViewUtils.runAnimationDrawable(isSelected, binding.imgMusicAnimation);
            binding.musicName.setText(music.title);
            binding.musicDuration.setText(millisecondsToTime(music));

            binding.getRoot().setOnClickListener(item -> {
                selectedItem = music;
                viewModel.updateSelectedMusic(music);
                if (music.resource != 0) {
                    ThreadExecutors.execute(ThreadExecutors.Where.WORKER, () -> {MusicEngine.getInstance().playMusic(music.resource);});
                } else {
                    MusicEngine.getInstance().stopMusic();
                }
            });
            return view;
        }

        private boolean isMusicSelected(Music music) {
            return music != null && music.resource != 0 && music == selectedItem;
        }

        private String millisecondsToTime(Music music) {
            if (music == null) return "00:00";
            if (music.resource == 0) return App.self().getString(R.string.off);
            long milliseconds = music.duration;
            long minutes = (milliseconds / 1000) / 60;
            long seconds = (milliseconds / 1000) % 60;
            String secs = seconds +"";
            String minute = minutes +"";
            if (seconds < 10) {
                secs = "0" + seconds;
            }

            if (minutes< 10) {
                minute = "0" + minutes;
            }

            return minute + ":" + secs;
        }
    }
}
