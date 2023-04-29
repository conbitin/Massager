/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 12:29 PM
 * Last modified 4/21/23, 12:29 PM
 */

package com.android.vibrate.massager.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.vibrate.massager.R;
import com.android.vibrate.massager.core.App;

import static com.android.vibrate.massager.view.MainActivity.POS_INTENSITY_PAGE;
import static com.android.vibrate.massager.view.MainActivity.POS_PATTERN;
import static com.android.vibrate.massager.view.MainActivity.POS_SETTINGS_PAGE;

public class AppTabIndicator extends FrameLayout {


    public AppTabIndicator(@NonNull Context context) {
        super(context);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTabSelected(POS_SETTINGS_PAGE);
    }

    public void setTabSelected(int pageSelected) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        switch (pageSelected) {
            case POS_SETTINGS_PAGE:
                setBackground(App.self().getDrawable(R.drawable.tab_settings_indicator));

                if (params != null) {
                    params.gravity = Gravity.LEFT|Gravity.BOTTOM;
                    setLayoutParams(params);
                }
                break;

            case POS_INTENSITY_PAGE:
                setBackground(App.self().getDrawable(R.drawable.tab_intensity_indicator));
                if (params != null) {
                    params.gravity = Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM;
                    setLayoutParams(params);
                }
                break;

            case POS_PATTERN:
                setBackground(App.self().getDrawable(R.drawable.tab_pattern_indicator));
                if (params != null) {
                    params.gravity = Gravity.RIGHT|Gravity.BOTTOM;
                    setLayoutParams(params);
                }
                break;
        }
    }


}
