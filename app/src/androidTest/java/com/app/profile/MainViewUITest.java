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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainViewUITest {


    private static final String PROFILE_NAME = "JIJO JOSEPH JOSE";
    private static final String PROFILE_TITLE = "Profile Information";

    @Rule
    public ActivityTestRule<MainActivity> mHomePageTestActivity = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void homeTitleTest() throws InterruptedException {

        //testing the header value in the mainview
        onView(withId(R.id.t_item_professional_title)).check(matches(withText(PROFILE_TITLE)));

        //Thread sleep of 5 seconds to load UI from API.
        Thread.sleep(5000);

        //Profile name verification, assumed to be loaded from API fetch.
        onView(withId(R.id.t_item_profile_name)).check(matches(withText(PROFILE_NAME)));
    }

}
