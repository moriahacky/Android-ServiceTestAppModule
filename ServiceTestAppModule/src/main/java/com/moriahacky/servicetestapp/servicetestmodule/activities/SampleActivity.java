package com.moriahacky.servicetestapp.servicetestmodule.activities;

import android.content.Intent;
import android.os.Bundle;

import com.moriahacky.servicetestapp.servicetestmodule.TestService;
import com.moriahacky.servicetestapp.servicetestmodule.R;
import com.moriahacky.servicetestapp.servicetestmodule.TestIntentService;
import com.moriahacky.servicetestapp.servicetestmodule.pojos.SamplePojo;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SampleActivity extends BaseActivity {

    private int mRequestNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ButterKnife.inject(this);
    }

    @OnClick(R.id.start_service_button)
    public void sendStartServiceRequest() {
        Intent i = new Intent(this, TestService.class);
        startService(i);
    }

    @OnClick(R.id.stop_service_button)
    public void sendStopServiceRequest() {
        Intent i = new Intent(this, TestService.class);
        stopService(i);
    }

    @OnClick(R.id.start_intent_service_button)
    public void sendStartIntentServiceRequest() {
        Intent i = new Intent(this, TestIntentService.class);
        Bundle extras = new Bundle();
        extras.putInt("request_no",  mRequestNumber++);
        i.putExtras(extras);
        startService(i);
    }

    @OnClick(R.id.stop_intent_service_button)
    public void sendStopIntentServiceRequest() {
        Intent i = new Intent(this, TestIntentService.class);
        Bundle extras = new Bundle();
        extras.putInt("request_no",  mRequestNumber++);
        i.putExtras(extras);
        stopService(i);
    }

    @OnClick(R.id.start_queued_intent_service_button)
    public void sendStartQueuedIntentServiceRequest() {
        Intent i = new Intent(this, TestIntentService.class);
        Bundle extras = new Bundle();
        extras.putInt("request_no",  mRequestNumber++);
        extras.putBoolean("isQueued", true);
        i.putExtras(extras);
        startService(i);
    }


    private void someMethodToIncludeAPojo() {
        SamplePojo tester = new SamplePojo();
        tester.someMethod();
    }
}
