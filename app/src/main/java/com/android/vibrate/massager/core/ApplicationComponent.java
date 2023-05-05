/*
 * Created by  on 9/11/21 10:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 8/19/21 9:05 AM
 */

package com.android.vibrate.massager.core;

import com.android.vibrate.massager.di.RepositoryModule;
import com.android.vibrate.massager.view.MainActivity;
import com.android.vibrate.massager.view.MusicActivity;
import com.android.vibrate.massager.view.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RepositoryModule.class)
public interface ApplicationComponent {
    /*Common*/
    void inject(MainActivity activity);
    void inject(BaseFragment fragment);
    void inject(MusicActivity activity);
}
