package com.example.takeflowers3;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<ElementActivity> activityActivityTestRule = new ActivityTestRule<>(ElementActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.takeflowers3", appContext.getPackageName());
    }

    @Test
    public void test1(){
        onView(withId(R.id.mailText)).check(matches(isDisplayed()));
    }
    public void test2(){
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }
    public void test3(){
        onView(withId(R.id.toListButton)).check(matches(isDisplayed()));
    }
}
