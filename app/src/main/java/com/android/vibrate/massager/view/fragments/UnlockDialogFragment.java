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
import androidx.fragment.app.DialogFragment;

import com.android.vibrate.massager.databinding.FragmentUnlockBinding;

public class UnlockDialogFragment extends DialogFragment {
    private FragmentUnlockBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.mBinding = FragmentUnlockBinding.inflate(inflater, container, false);
        this.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        this.mBinding.unlockButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                dismiss();
                return true;
            }
        });

        return this.mBinding.getRoot();
    }
}
