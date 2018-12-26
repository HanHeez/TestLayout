package com.gtv.hanhee.testlayout.api.support;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class Logger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        Log.i("Logger", message);
    }
}
