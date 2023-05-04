/*
 * Copyright (c) 2023
 * Create by  on 4/25/23, 1:46 PM
 * Last modified 4/25/23, 1:46 PM
 */

package com.android.vibrate.massager.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.vibrate.massager.core.AppObservable;

import java.util.Observable;
import java.util.Observer;

public class BaseDialog extends Dialog implements Observer {
    public BaseDialog(@NonNull final Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.getWindow().getAttributes().format = PixelFormat.TRANSLUCENT;//trong suot
    }

    public BaseDialog(@NonNull final Context context, final int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull final Context context, final boolean cancelable, @Nullable final OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void update(final Observable observable, final Object o) {

    }

    @Override
    public void onAttachedToWindow() {
        AppObservable.getInstance().addObserver(this);
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        AppObservable.getInstance().deleteObserver(this);
        super.onDetachedFromWindow();
    }
}
