package com.app.profile;


import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import views.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class NetworkAvailiabilityTest {

    @Rule
    public ActivityTestRule<MainActivity> mHomePageTestActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkNetworkRetryUIVisibility() throws InterruptedException {

        //Making Thread sleep as on the load of Activity API call starts which check for network availability
        Thread.sleep(2000);

        //Calling the Activity Method & for Test purpose PASSING networkAvailability as FALSE
        mHomePageTestActivity.getActivity().runOnUiThread(() -> {
            (mHomePageTestActivity.getActivity()).updateUIForNetworkAvailability(false);
        });

        //Checking the Network UI visibility for the NO NETWORK CASE as simulated above
        onView(withId(R.id.layout_home_no_network)).check(matches(isDisplayed()));
    }
}
