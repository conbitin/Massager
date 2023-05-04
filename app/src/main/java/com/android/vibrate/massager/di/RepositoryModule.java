/*
 * Created by  on 1/5/22 11:43 AM
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 10/7/21 11:47 AM
 */

package com.android.vibrate.massager.di;


import com.android.vibrate.massager.repository.AppRepository;
import com.android.vibrate.massager.repository.AppRepositoryImp;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepositoryModule {
    @Binds
    AppRepository provideAppRepository(AppRepositoryImp appRepositoryImp);
}
