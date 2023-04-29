/*
 * Copyright (c) 2023
 * Create by  on 4/22/23, 6:13 PM
 * Last modified 4/22/23, 6:13 PM
 */

package com.android.vibrate.massager.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.App;
import com.android.vibrate.massager.databinding.ActivityUnlockVibrationBinding;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.view.model.AppViewModel;

import javax.inject.Inject;

public class UnlockPatternActivity extends AppCompatActivity {
    ActivityUnlockVibrationBinding mBinding;
    @Inject
    AppViewModel mAppViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.self().getAppComponent().inject(this);
        mBinding = ActivityUnlockVibrationBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.unlockButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                Toast.makeText(UnlockPatternActivity.this, "Unlocked", Toast.LENGTH_LONG).show();
                finish();
                return true;
            }
        });

        String pattern = getIntent().getStringExtra("PATTERN_NAME");
        if (TextUtils.isEmpty(pattern)) {
            pattern =  App.self().getString(R.string.app_name);
        }
        mBinding.itemLockedText.setText(getString(R.string.message_is_locked, pattern));
    }

    @Override
    public void onBackPressed() {

    }
}