package com.moriahacky.servicetestapp.servicetestmodule;

import android.app.Application;

import timber.log.Timber;

public class BaseApplication
        extends Application {

    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new CrashReportingTree());
//        Crashlytics.start(this);
    }
}
