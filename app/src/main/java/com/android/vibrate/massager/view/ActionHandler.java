/*
 * Copyright (c) 2023
 * Create by  on 4/22/23, 2:11 PM
 * Last modified 4/22/23, 2:11 PM
 */

package com.android.vibrate.massager.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.android.vibrate.massager.BuildConfig;
import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.AppObservable;
import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.databinding.DialogNoVibrationGuideBinding;
import com.android.vibrate.massager.databinding.DialogPatternCreatorBinding;
import com.android.vibrate.massager.engine.Pattern;
import com.android.vibrate.massager.engine.PatternCreationLoader;
import com.android.vibrate.massager.view.dialogs.CreationPatternDialog;
import com.android.vibrate.massager.view.dialogs.NoVibrationDialog;

import java.util.Observable;
import java.util.Observer;


public class ActionHandler {
    private static final String TAG = ActionHandler.class.getSimpleName();

    public static void share(Context context) {
        if (context == null) return;
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name));
            String urlForSharing = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, urlForSharing);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            Log.wtf(TAG, "share handle error", e);
        }
    }

    public static void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            Log.wtf(TAG, "rateUpp handle error, device lacks google play app???", e);
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
            } catch (Exception ex) {
                Log.wtf(TAG, "rateUpp handle error", ex);
            }
        }
    }

    public static void sendFeedback(Activity activity) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback about " + activity.getString(R.string.app_name));
            intent.putExtra(Intent.EXTRA_TEXT, "");
            activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.settings_send_feedback)));
        } catch (Exception e) {
            Log.wtf(TAG, "sendFeedback handle error, device lacks email app???", e);
        }
    }

    public static void noVibration(Activity activity) {
        Dialog dialog = new NoVibrationDialog(activity);
        dialog.show();
    }

    public static void createVibration(Activity activity) {
        CreationPatternDialog dialog = new CreationPatternDialog(activity);
        dialog.show();
    }

    public static void unlockVibration(Context context, String patternName) {
        Intent intent = new Intent(context, UnlockPatternActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("PATTERN_NAME", patternName);
        context.startActivity(intent);
    }

    public static void unlockEverything(Context context) {
        Intent intent = new Intent(context, UnlockEverythingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void musicChooser(Context context) {
        Intent intent = new Intent(context, MusicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
