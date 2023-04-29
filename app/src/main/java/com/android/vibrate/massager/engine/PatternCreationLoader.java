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
        loadPatternCreatorList();
    }

    public static PatternCreationLoader getInstance() {
        if (sInstance == null) {
            synchronized (PatternCreationLoader.class) {
                if (sInstance == null) {
                    sInstance = new PatternCreationLoader();
                }
            }
        }
        return sInstance;
    }

    public void saveCreatedPattern(Pattern pattern) {
        if (pattern == null || pattern.pattern == null || pattern.pattern.length == 0 || pattern.type != Pattern.TYPE_CUSTOM) return;
        if (TextUtils.isEmpty(pattern.title)) pattern.title = "No Name";
        mPatterns.add(0, pattern);
        saveCreatedPattern(mPatterns);
        PatternProvider.getInstance().refreshListAfterCreateSuccess();
    }

    private void saveCreatedPattern(ArrayList<Pattern> list)
    {
        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(list);
        Settings.setSettings(Settings.KEY_PATTERN, hashMapString);
    }

    public ArrayList<Pattern> getAllCreatedPattern() {
        return mPatterns;
    }

    public void loadPatternCreatorList() {
        try {
            String storedPatternListString = Settings.getStringSettings(Settings.KEY_PATTERN, "{}");
            java.lang.reflect.Type type = new TypeToken<ArrayList<Pattern>>() {
            }.getType();
            Gson gson = new Gson();
            mPatterns = gson.fromJson(storedPatternListString, type);
        } catch (Exception e) {}
        if (mPatterns == null) {
            mPatterns =new ArrayList<>();
        }
    }

}
