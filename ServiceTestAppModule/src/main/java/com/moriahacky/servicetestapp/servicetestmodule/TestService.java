package com.moriahacky.servicetestapp.servicetestmodule;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import timber.log.Timber;

public class TestService
    extends Service {

    private Handler mHandlerForNonUiThread = null;
    private Runnable mSyncRunnable = null;

    @Override
    public void onCreate() {
        Timber.d("Creating Service");
        super.onCreate();
        // TODO: Register with an Otto Scoped bus
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("Received start id " + startId + ": " + intent);

        if (mHandlerForNonUiThread != null) {
            Timber.d("Killing a previous running operation");
            mHandlerForNonUiThread.getLooper().getThread().interrupt();
        }
        getHandlerForNonUiThread().post(getRepeatingSyncRunnable());

//        return START_STICKY;  // We continue running until it explicitly stopped
        return START_NOT_STICKY;
    }

    @Override
    public synchronized void onDestroy() {
        super.onDestroy();

        // cancel thread
    }

    // TODO: @Subscribe to sync requests

    // TODO: @Publish Sync results
    // if onStartcommand called, then i probably don't need to do this


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private Handler getHandlerForNonUiThread() {
        if (mHandlerForNonUiThread != null) {
            Timber.d("getting existing handler for non ui thread");
            return mHandlerForNonUiThread;
        }

        Timber.d("getting a new handler for non ui thread");
        HandlerThread ht = new HandlerThread("SyncServiceHandlerThread");
        ht.start();

        mHandlerForNonUiThread = new Handler(ht.getLooper());
        return mHandlerForNonUiThread;
    }

    private Runnable getRepeatingSyncRunnable() {

        if (mSyncRunnable != null) {
            Timber.d("returning existing Sync Runnable");
            return mSyncRunnable;
        }

        Timber.d("creating new Sync Runnable");
        mSyncRunnable = new Runnable() {

            @Override
            public void run() {
                // do actual sync
                doActualSync();

                // repeat in 10 seconds
                getHandlerForNonUiThread().postDelayed(this, 10000);
            }
        };
        return mSyncRunnable;
    }


    private void doActualSync() {
        Timber.d("Start the actual Sync");

        // do something
        try {
            Timber.d("Doing a long operation that'll take 2 seconds to complete");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Timber.d("Existing actual Sync method");
    }
}
