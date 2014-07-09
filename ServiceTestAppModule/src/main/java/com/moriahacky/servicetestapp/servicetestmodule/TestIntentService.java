package com.moriahacky.servicetestapp.servicetestmodule;

import android.content.Intent;

import timber.log.Timber;

public class TestIntentService extends BaseIntentService {

    private boolean mServiceAlreadyRunningInBg = false;
    private int mRequestNumber;

    public TestIntentService() {
        super("com.moriahacky.servicetestapp.servicetestmodule.TestIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("onStartCommand - %s", String.valueOf(intent.getExtras().getInt("request_no")));

        super.onStartCommand(intent, flags, startId);

        if (!mServiceAlreadyRunningInBg) {
            mServiceAlreadyRunningInBg = true;
        } else {
            if (!intent.getBooleanExtra("isQueued", false)) {
                clearQueue();
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Timber.d("onHandleIntent");
        mRequestNumber = intent.getExtras().getInt("request_no");
        Timber.d("Processing request %s", String.valueOf(mRequestNumber));
        doActualSync();
    }

    private void doActualSync() {
        Timber.d("Start the long sync operation");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Timber.d("Done processing request %s", mRequestNumber);
        mServiceAlreadyRunningInBg = false;
    }
}
