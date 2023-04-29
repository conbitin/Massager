/*
 * Created by  on 3/23/23 12:41 AM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 3/23/23 12:39 AM
 */

package com.android.vibrate.massager.api;

import android.util.Log;

import com.android.vibrate.massager.api.ip.response.ErrorResponse;
import com.android.vibrate.massager.core.ThreadExecutors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.CompletableFuture;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class CloudApi {
    private static CloudApi sInstance;

    private CloudApi() {
    }

    private Gson parser = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").setLenient().disableHtmlEscaping().create();

    public static CloudApi getInstance() {
        if (sInstance == null) {
            synchronized (CloudApi.class) {
                if (sInstance == null) {
                    sInstance = new CloudApi();
                }
            }
        }
        return sInstance;
    }


    private void handleResponse(Response response, RequestListener listener) {
        if (response == null) {
            listener.failed();
            return;
        }
        if (response.isSuccessful()) {
            listener.success(response.body());
        } else {
            try {
                String errorBody = response.errorBody().string();
                Log.e("CloudApi", "handleResponse:" + errorBody);
                listener.error(parser.fromJson(errorBody, ErrorResponse.class));
            } catch (Exception e) {
                Log.e("CloudApi", "handleResponse", e);
                listener.failed();
            }
        }
    }

    public interface RequestListener<T> {
        void success(T data);

        void error(ErrorResponse error);

        void failed();
    }

    public Gson parser() {
        return parser;
    }

    public void getPublicIP(RequestListener listener) {
        ThreadExecutors.execute(ThreadExecutors.Where.NETWORK, () -> {
            GetIPRequest publicIPRequest = getWebServiceForCheckPublicIP(GetIPRequest.class);
            handleResponse(WebServiceUtils.executeApi(publicIPRequest.getPublicIP()), listener);
        });
    }


    private interface GetIPRequest {
        @Headers({"Content-Type: application/json"})
        @GET(Endpoint.API_GET_PUBLIC_ID_REQUEST)
        CompletableFuture<Response<String>> getPublicIP();

    }

    private <T> T getWebServiceForCheckPublicIP(Class<T> clazz) {
        return WebServiceUtils.getWebService(clazz, Endpoint.getHostGetPublicIp());
    }

}
