package com.moriahacky.servicetestapp.servicetestmodule;

import android.content.Intent;

import timber.log.Timber;

public class TestSingleRequestIntentService
    extends SingleRequestIntentService {


    public TestSingleRequestIntentService() {
        super("com.moriahacky.servicetestapp.servicetestmodule.TestQueueIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("--- onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("--- onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Timber.d("--- onHandleIntent");
        Timber.d("Start the long sync operation");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Timber.d("--- Task done");
        serviceHasFinishedWork();
    }
}
