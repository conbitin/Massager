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
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.self().getAppComponent().inject(this);
        this.mBinding = ActivityMusicBinding.inflate(this.getLayoutInflater());
        this.setContentView(this.mBinding.getRoot());
        AppObservable.getInstance().addObserver(this);
        this.mBinding.musicList.setAdapter(this.mMusicAdapter = new MusicAdapter(this, R.layout.music_item, MusicProvider.getInstance().getMusics(), this.mAppViewModel));
        this.mBinding.backButton.setOnClickListener(view ->  {
            this.onBackPressed(); });
        this.mBinding.musicDone.setOnClickListener(view -> {
            MusicEngine.getInstance().stopMusic();
            this.finish(); });

        this.mAppViewModel.selectedMusicData().observe(this, music -> {
            this.mMusicAdapter.notifyDataSetChanged();});
    }

    @Override
    public void onBackPressed() {
        this.mAppViewModel.updateSelectedMusic(null);
        MusicEngine.getInstance().stopMusic();
        super.onBackPressed();
    }

    @Override
    public void update(final Observable observable, final Object data) {

    }

    @Override
    protected void onDestroy() {
        AppObservable.getInstance().deleteObserver(this);
        super.onDestroy();
    }

    private static class MusicAdapter extends ArrayAdapter<Music> {
        AppViewModel viewModel;
        private Music selectedItem;

        public MusicAdapter(@NonNull final Context context, final int resource, @NonNull final List list, final AppViewModel viewModel) {
            super(context, resource, list);
            this.viewModel = viewModel;
            this.selectedItem = viewModel.getSelectedMusic();
        }

        @Override
        public View getView(final int i, View view, final ViewGroup viewGroup) {
            MusicItemBinding binding = null;
            if (view == null) {
                final LayoutInflater inflater = LayoutInflater.from(this.getContext());
                view = inflater.inflate(R.layout.music_item, viewGroup, false);
            }
            final Music music = this.getItem(i);
            binding = MusicItemBinding.bind(view);
            final boolean isSelected = this.isMusicSelected(music);
            binding.getRoot().setActivated(isSelected);
            binding.imgChecked.setVisibility(isSelected ? View.VISIBLE :View.GONE);
            binding.imgPlay.setActivated(isSelected);
            binding.imgPlay.setVisibility(music.resource != 0 ? View.VISIBLE :View.GONE);
            binding.itemRoot.setActivated(isSelected);
            binding.imgMusicAnimation.setVisibility(isSelected ? View.VISIBLE :View.GONE);
            ViewUtils.runAnimationDrawable(isSelected, binding.imgMusicAnimation);
            binding.musicName.setText(music.title);
            binding.musicDuration.setText(this.millisecondsToTime(music));

            binding.getRoot().setOnClickListener(item -> {
                this.selectedItem = music;
                this.viewModel.updateSelectedMusic(music);
                if (music.resource != 0) {
                    ThreadExecutors.execute(ThreadExecutors.Where.WORKER, () -> {MusicEngine.getInstance().playMusic(music.resource);});
                } else {
                    MusicEngine.getInstance().stopMusic();
                }
            });
            return view;
        }

        private boolean isMusicSelected(final Music music) {
            return music != null && music.resource != 0 && music == this.selectedItem;
        }

        private String millisecondsToTime(final Music music) {
            if (music == null) return "00:00";
            if (music.resource == 0) return App.self().getString(R.string.off);
            final long milliseconds = music.duration;
            final long minutes = (milliseconds / 1000) / 60;
            final long seconds = (milliseconds / 1000) % 60;
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
