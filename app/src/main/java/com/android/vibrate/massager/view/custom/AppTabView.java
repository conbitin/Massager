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


    public AppTabView(@NonNull final Context context) {
        super(context);
        this.setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTabSelected(POS_SETTINGS_PAGE);
    }

    public AppTabView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setTabSelected(POS_SETTINGS_PAGE);
    }

    public void setTabSelected(final int pageSelected) {
        switch (pageSelected) {
            case POS_SETTINGS_PAGE:
                this.setBackground(App.self().getDrawable(R.drawable.tab_left_bg));
                break;

            case POS_INTENSITY_PAGE:
                this.setBackground(App.self().getDrawable(R.drawable.tab_middle_bg));
                break;

            case POS_PATTERN:
                this.setBackground(App.self().getDrawable(R.drawable.tab_right_bg));
                break;
        }
    }


}
