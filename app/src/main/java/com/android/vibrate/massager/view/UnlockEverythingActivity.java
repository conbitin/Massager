/*
 * Copyright (c) 2023
 * Create by  on 4/22/23, 6:13 PM
 * Last modified 4/22/23, 6:13 PM
 */

package com.android.vibrate.massager.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.vibrate.massager.databinding.ActivityUnlockEverythingBinding;
import com.android.vibrate.massager.databinding.ActivityUnlockVibrationBinding;

public class UnlockEverythingActivity extends AppCompatActivity {
    ActivityUnlockEverythingBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUnlockEverythingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.tryForFreeButton.setOnClickListener(view -> {
            Toast.makeText(UnlockEverythingActivity.this, "Try For Free Clicked", Toast.LENGTH_LONG).show();
            finish();
        });

        mBinding.backButton.setOnClickListener(view -> { finish(); });
    }

    @Override
    public void onBackPressed() {

    }
}
