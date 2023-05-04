/*
 * Created by  on 3/23/23 7:14 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 3/23/23 7:14 PM
 */

package com.android.vibrate.massager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.vibrate.massager.core.App;

import java.util.Set;

public class Settings {

    private static final String KEY_PREFERENCES = "com.android.vibrate.massager_preferences";

    public static final String KEY_PATTERN = "PATTERN";

    public static void setSettings(final String key, final String text) {
        final SharedPreferences pref = App.self().getSharedPreferences(Settings.KEY_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor e = pref.edit();
        e.putString(key, text);
        e.apply();
    }

    public static String getStringSettings(final String key, final String defaultValue) {
        final SharedPreferences pref = App.self().getSharedPreferences(Settings.KEY_PREFERENCES, Context.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

}

