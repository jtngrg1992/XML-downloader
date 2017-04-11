package com.example.jatingarg.xmlparser;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;


/**
 * Created by jatingarg on 11/04/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.jatingarg.xmlparser", appContext.getPackageName());
    }

    @Test
    public void testForCompleteData(){
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything())
                .inAdapterView((withId(R.id.listView)))
                .atPosition(9)
                .check(matches(isDisplayed()));
    }
}
