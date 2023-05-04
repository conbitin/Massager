/*
 * Created by  on 9/11/21 10:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 8/19/21 9:05 AM
 */
package com.android.vibrate.massager.core;

import java.util.Observable;

public class AppObservable extends Observable {
    private static AppObservable sInstance;

    private AppObservable() {
    }

    public static AppObservable getInstance() {
        if (AppObservable.sInstance == null) {
            synchronized (AppObservable.class) {
                if (AppObservable.sInstance == null) {
                    AppObservable.sInstance = new AppObservable();
                }
            }
        }
        return AppObservable.sInstance;
    }

    @Override
    public void notifyObservers(final Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }
}
