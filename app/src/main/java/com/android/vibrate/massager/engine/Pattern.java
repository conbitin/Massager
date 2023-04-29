/*
 * Copyright (c) 2023
 * Create by  on 4/23/23, 5:23 PM
 * Last modified 4/23/23, 5:23 PM
 */

package com.android.vibrate.massager.engine;

import com.android.vibrate.massager.R;
import com.google.gson.annotations.SerializedName;

public class Pattern {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_CREATE = 1;
    public static final int TYPE_LOCK = 2;
    public static final int TYPE_CUSTOM = 3;

    @SerializedName("pattern")
    public long[] pattern = new long[] {};

    public int pic = R.drawable.ic_pattern_creation_icon;

    @SerializedName("title")
    public String title;

    @SerializedName("type")
    public int type = TYPE_LOCK;
}
