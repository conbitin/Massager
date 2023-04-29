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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false);
        mBinding.notVibration.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_NOT_VIBRATION);});
        mBinding.unlockEverything.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_UNLOCK_EVERYTHING);});
        mBinding.rateTheApp.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_RATE_THE_APP);});
        mBinding.shareTheApp.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_SHARE_THE_APP);});
        mBinding.sendFeedback.setOnClickListener(view -> { sendEvent(Event.EVENT_SHOW_SEND_FEEDBACK);});

        return mBinding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
