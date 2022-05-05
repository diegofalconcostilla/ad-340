package com.tutorials.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.tutorials.helloworld.RecyclerViewMatcher.withRecyclerView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {
    @Rule
    public ActivityScenarioRule<WelcomeActivity> activityTestRule
            = new ActivityScenarioRule<>(WelcomeActivity.class);

    @Test
    public void succesfulNavigationToSettings(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.settingsTextView)).check(matches(withText("This is where settings will go")));
    }

    @Test
    public void succesfulNavigationToMatches(){
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.matches_menu_item)).perform(click());

        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Dog Capone"))));
        onView(withRecyclerView(R.id.recycler_view).atPosition(1))
                .check(matches(hasDescendant(withText("Lazy Frankie"))));
        onView(withRecyclerView(R.id.recycler_view).atPosition(2))
                .check(matches(hasDescendant(withText("Space Cat"))));
    }
}