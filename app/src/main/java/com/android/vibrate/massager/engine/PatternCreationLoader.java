/*
 * Copyright (c) 2023
 * Create by  on 4/24/23, 5:12 PM
 * Last modified 4/24/23, 5:12 PM
 */

package com.android.vibrate.massager.engine;

import android.text.TextUtils;

import com.android.vibrate.massager.utils.Settings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PatternCreationLoader {
    private static final String TAG = PatternCreationLoader.class.getSimpleName();
    private static PatternCreationLoader sInstance;

    private ArrayList<Pattern> mPatterns = new ArrayList<>();


    private PatternCreationLoader() {
        this.loadPatternCreatorList();
    }

    public static PatternCreationLoader getInstance() {
        if (PatternCreationLoader.sInstance == null) {
            synchronized (PatternCreationLoader.class) {
                if (PatternCreationLoader.sInstance == null) {
                    PatternCreationLoader.sInstance = new PatternCreationLoader();
                }
            }
        }
        return PatternCreationLoader.sInstance;
    }

    public void saveCreatedPattern(final Pattern pattern) {
        if (pattern == null || pattern.pattern == null || pattern.pattern.length == 0 || pattern.type != Pattern.TYPE_CUSTOM) return;
        if (TextUtils.isEmpty(pattern.title)) pattern.title = "No Name";
        this.mPatterns.add(0, pattern);
        this.saveCreatedPattern(this.mPatterns);
        PatternProvider.getInstance().refreshListAfterCreateSuccess();
    }

    private void saveCreatedPattern(final ArrayList<Pattern> list)
    {
        //convert to string using gson
        final Gson gson = new Gson();
        final String hashMapString = gson.toJson(list);
        Settings.setSettings(Settings.KEY_PATTERN, hashMapString);
    }

    public ArrayList<Pattern> getAllCreatedPattern() {
        return this.mPatterns;
    }

    public void loadPatternCreatorList() {
        try {
            final String storedPatternListString = Settings.getStringSettings(Settings.KEY_PATTERN, "{}");
            final java.lang.reflect.Type type = new TypeToken<ArrayList<Pattern>>() {
            }.getType();
            final Gson gson = new Gson();
            this.mPatterns = gson.fromJson(storedPatternListString, type);
        } catch (final Exception e) {}
        if (this.mPatterns == null) {
            this.mPatterns =new ArrayList<>();
        }
    }

}
