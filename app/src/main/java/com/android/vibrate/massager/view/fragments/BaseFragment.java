/*
 * Copyright (c) 2023
 * Create by  on 4/21/23, 11:20 AM
 * Last modified 4/21/23, 11:20 AM
 */

package com.android.vibrate.massager.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.vibrate.massager.core.App;
import com.android.vibrate.massager.core.AppObservable;
import com.android.vibrate.massager.core.Event;
import com.android.vibrate.massager.view.model.AppViewModel;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

public class BaseFragment extends Fragment implements Observer {

    @Inject
    AppViewModel mAppViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.self().getAppComponent().inject(this);
        AppObservable.getInstance().addObserver(this);
    }

    @Override
    public void onDestroy() {
        AppObservable.getInstance().deleteObserver(this);
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof Event) {
            handleEvent((Event) data);
        }
    }

    public void sendEvent(int event) {
        AppObservable.getInstance().notifyObservers(Event.of(event));
    }

    public void handleEvent(Event event) {
        //override this function in child class to handle the received event if need
    }
}
