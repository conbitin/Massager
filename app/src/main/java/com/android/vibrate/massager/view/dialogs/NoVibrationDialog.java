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
    public NoVibrationDialog(@NonNull final Activity activity) {
        super(activity);
        final DialogNoVibrationGuideBinding binding = DialogNoVibrationGuideBinding.inflate(activity.getLayoutInflater());
        this.setContentView(binding.getRoot());

        binding.backButton.setOnClickListener(view -> {
            this.dismiss(); });
        binding.gotItButton.setOnClickListener(view -> {
            this.dismiss(); });
        binding.goToSettings.setOnClickListener(view -> {
            final Intent intent = new Intent(android.provider.Settings.ACTION_SOUND_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
            this.dismiss();
        });

        this.setCancelable(true);
    }

}
