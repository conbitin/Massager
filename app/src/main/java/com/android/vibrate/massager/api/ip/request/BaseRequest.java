/*
 * Created by  on 3/23/23 12:41 AM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 3/23/23 12:39 AM
 */

package com.android.vibrate.massager.api.ip.request;

import androidx.annotation.NonNull;

import com.android.vibrate.massager.api.CloudApi;

public class BaseRequest {

    @NonNull
    @Override
    public String toString() {
        return CloudApi.getInstance().parser().toJson(this);
    }
}
