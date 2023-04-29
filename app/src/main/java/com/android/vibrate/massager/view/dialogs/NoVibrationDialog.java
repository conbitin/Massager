/*
 * Copyright (c) 2023
 * Create by  on 4/25/23, 1:58 PM
 * Last modified 4/25/23, 1:58 PM
 */

package com.android.vibrate.massager.view.dialogs;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.android.vibrate.massager.databinding.DialogNoVibrationGuideBinding;

public class NoVibrationDialog extends BaseDialog {
    public NoVibrationDialog(@NonNull Activity activity) {
        super(activity);
        DialogNoVibrationGuideBinding binding = DialogNoVibrationGuideBinding.inflate(activity.getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButton.setOnClickListener(view -> { dismiss(); });
        binding.gotItButton.setOnClickListener(view -> {dismiss(); });
        binding.goToSettings.setOnClickListener(view -> {
            Intent intent = new Intent(android.provider.Settings.ACTION_SOUND_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
            dismiss();
        });

        setCancelable(true);
    }

}
