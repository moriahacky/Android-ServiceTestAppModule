package com.moriahacky.servicetestapp.servicetestmodule;

import android.content.Intent;

/**
 * An intent service that processes only a single request at any point of time (by default)
 *
 * Subsequent requests are handled by {@link #onStartCommand(android.content.Intent, int, int)}
 * which acts as a guard clause to {@link #onHandleIntent(android.content.Intent)}.
 * The incoming request is simply cleared (unless explicitly asked not to, by {@link #setForciblyQueuedOperation()})
 *
 * It is important to remember that
 */
public abstract class SingleRequestIntentService
    extends BaseIntentService {

    private boolean _serviceTaskRunningInBg = false;
    private boolean _isForciblyQueuedOperation = false;

    public SingleRequestIntentService(String name) {
        super(name);
    }

    /**
     * Remember to call serviceHasFinishedWork(); at the end of this method
     */
    @Override
    protected abstract void onHandleIntent(Intent intent);

    /**
     * The documentation says don't override this method, but we do it anyway because
     * this is the only way we can control the request queue for the IntentService.
     *
     * When you send a request to the intent service it goes into the queue but
     * it is the call to {@link #clearQueue()} that actually removes this duplicate request and
     * prevent it from executing after the currently running operation.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int resultCode = super.onStartCommand(intent, flags, startId);
        if (!_serviceTaskRunningInBg) {
            _serviceTaskRunningInBg = true;
        } else {
            if (!_isForciblyQueuedOperation) {
                clearQueue();
            }
        }
        return resultCode;
    }

    public void serviceHasFinishedWork() {
        _serviceTaskRunningInBg = false;
    }

    /**
     * If you're using an IntentService, more often than not, you don't want to call this method
     */
    public void setForciblyQueuedOperation() {
        _isForciblyQueuedOperation = true;
    }
}