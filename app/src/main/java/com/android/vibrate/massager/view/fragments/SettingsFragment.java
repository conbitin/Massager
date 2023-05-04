/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 11:19 AM
 * Last modified 4/21/23, 11:19 AM
 */

package com.android.vibrate.massager.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.databinding.FragmentSettingsBinding;

public class SettingsFragment extends BaseFragment {
    private FragmentSettingsBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.mBinding = FragmentSettingsBinding.inflate(inflater, container, false);
        this.mBinding.notVibration.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_NOT_VIBRATION);});
        this.mBinding.unlockEverything.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_UNLOCK_EVERYTHING);});
        this.mBinding.rateTheApp.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_RATE_THE_APP);});
        this.mBinding.shareTheApp.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_SHARE_THE_APP);});
        this.mBinding.sendFeedback.setOnClickListener(view -> {
            this.sendEvent(Event.EVENT_SHOW_SEND_FEEDBACK);});

        return this.mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mBinding = null;
    }
}
