package com.android.vibrate.massager.core;

import android.app.Application;

public class App extends Application {

    private static App sInstance;
    ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        App.sInstance = this;
        this.appComponent = DaggerApplicationComponent.builder().build();
    }

    public static App self() {
        return App.sInstance;
    }

    public ApplicationComponent getAppComponent() {
        return this.appComponent;
    }
}
