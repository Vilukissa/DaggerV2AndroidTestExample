package com.calicode.daggertestexample.uitest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.calicode.daggertestexample.MainActivity;
import com.calicode.daggertestexample.R;
import com.calicode.daggertestexample.watering.BrokenTestPump;
import com.calicode.daggertestexample.watering.HighRatePump;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PumpValueTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testBrokenLowRatePump() {
        Espresso.onView(ViewMatchers.withId(R.id.lowRatePumpSwitch)).perform(ViewActions.click());

        String expectedText = String.valueOf((int) (BrokenTestPump.BROKEN_FLOW_RATE * 100));
        Espresso.onView(ViewMatchers.withId(R.id.lowRatePumpFlowValue))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedText)));
    }

    @Test
    public void testIntactHighRatePump() {
        Espresso.onView(ViewMatchers.withId(R.id.highRatePumpSwitch)).perform(ViewActions.click());

        String expectedText = String.valueOf((int) (HighRatePump.HIGH_FLOW_RATE * 100));
        Espresso.onView(ViewMatchers.withId(R.id.highRatePumpFlowValue))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedText)));
    }
}
