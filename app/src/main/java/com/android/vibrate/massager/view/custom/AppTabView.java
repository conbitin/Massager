/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 12:29 PM
 * Last modified 4/21/23, 12:29 PM
 */

package com.android.vibrate.massager.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.App;

import static com.android.vibrate.massager.view.MainActivity.POS_INTENSITY_PAGE;
import static com.android.vibrate.massager.view.MainActivity.POS_PATTERN;
import static com.android.vibrate.massager.view.MainActivity.POS_SETTINGS_PAGE;

public class AppTabView extends FrameLayout {


    public AppTabView(@NonNull Context context) {
        super(context);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public void setTabSelected(int pageSelected) {
        switch (pageSelected) {
            case POS_SETTINGS_PAGE:
                setBackground(App.self().getDrawable(R.drawable.tab_left_bg));
                break;

            case POS_INTENSITY_PAGE:
                setBackground(App.self().getDrawable(R.drawable.tab_middle_bg));
                break;

            case POS_PATTERN:
                setBackground(App.self().getDrawable(R.drawable.tab_right_bg));
                break;
        }
    }


}
