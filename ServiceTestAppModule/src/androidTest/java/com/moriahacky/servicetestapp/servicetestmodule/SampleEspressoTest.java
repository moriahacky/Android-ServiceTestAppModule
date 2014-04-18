package com.moriahacky.servicetestapp.servicetestmodule;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.moriahacky.servicetestapp.servicetestmodule.R;
import com.moriahacky.servicetestapp.servicetestmodule.activities.SampleActivity;

import static android.test.ViewAsserts.assertOnScreen;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

public class SampleEspressoTest
        extends ActivityInstrumentationTestCase2<SampleActivity> {

    private SampleActivity mActivity;
    private TextView mHelloWorldTextView;

    @SuppressWarnings( "deprecation" )
    public SampleEspressoTest() {
        super("com.moriahacky.servicetestapp.servicetestmodule", SampleActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
        mHelloWorldTextView = (TextView) mActivity.findViewById(R.id.txt_sample);
    }

    public void testTextView() {
        assertOnScreen(mActivity.getWindow().getDecorView(), mHelloWorldTextView);
    }

    public void testLabel() {
        onView(withId(R.id.txt_sample)).check(matches(withText("Hello world!")));
    }

    public void testFalseLabel() {
        onView(withId(R.id.txt_sample)).check(matches(withText("What a label!")));
    }
}
