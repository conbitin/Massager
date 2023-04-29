package com.android.vibrate.massager.api.ip.response;

/*
 * Created by  on 9/15/21 11:24 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 9/15/21 11:24 PM
 */

import androidx.annotation.NonNull;

import com.android.vibrate.massager.api.CloudApi;


public class BaseResponse {

    @NonNull
    @Override
    public String toString() {
        return CloudApi.getInstance().parser().toJson(this);
    }

    public <T extends BaseResponse> T copy(Class<T> tClass) {
        return CloudApi.getInstance().parser().fromJson(this.toString(), tClass);
    }
}
