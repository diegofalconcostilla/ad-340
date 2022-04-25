package com.tutorials.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void successfulFormSubmit(){
        onView(withId(R.id.names)).perform(replaceText("Diego Falcon"));
        onView(withId(R.id.email)).perform(replaceText("diegofalconc@hotmail.com"));
        onView(withId(R.id.username)).perform(replaceText("diegofalconc"));
        onView(withId(R.id.date_picker_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).check(matches(withText("Welcome diegofalconc")));
    }

    @Test
    public void successfulFormBackCleaning(){
        onView(withId(R.id.names)).perform(replaceText("Diego Falcon"));
        onView(withId(R.id.email)).perform(replaceText("diegofalconc@hotmail.com"));
        onView(withId(R.id.username)).perform(replaceText("diegofalconc"));
        onView(withId(R.id.date_picker_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.names)).check(matches(withText("")));
        onView(withId(R.id.username)).check(matches(withText("")));
        onView(withId(R.id.email)).check(matches(withText("")));
    }

    @Test
    public void failWithoutName() {
        onView(withId(R.id.names)).perform(replaceText(""));
        onView(withId(R.id.email)).perform(replaceText("diegofalconc@hotmail.com"));
        onView(withId(R.id.username)).perform(replaceText("diegofalconc"));
        onView(withId(R.id.date_picker_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("Diego Falcon")).check(doesNotExist());
    }

    @Test
    public void failWithoutBirthDate() {
        onView(withId(R.id.names)).perform(replaceText("Diego Falcon"));
        onView(withId(R.id.email)).perform(replaceText("diegofalconc@hotmail.com"));
        onView(withId(R.id.username)).perform(replaceText("diegofalconc"));
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("1/2/2000")).check(doesNotExist());
    }

    @Test
    public void failWithoutEmail() {
        onView(withId(R.id.names)).perform(replaceText("Diego Falcon"));
        onView(withId(R.id.username)).perform(replaceText("diegofalconc"));
        onView(withId(R.id.date_picker_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("diegofalconc@hotmail.com")).check(doesNotExist());
    }

    @Test
    public void failWithoutUsername() {
        onView(withId(R.id.names)).perform(replaceText("Diego Falcon"));
        onView(withId(R.id.email)).perform(replaceText("diegofalconc@hotmail.com"));
        onView(withId(R.id.username)).perform(replaceText(""));
        onView(withId(R.id.date_picker_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("diegofalconc")).check(doesNotExist());
    }

    @Test
    public void failWithoutProperAge() {
        onView(withId(R.id.names)).perform(replaceText("Diego Falcon"));
        onView(withId(R.id.email)).perform(replaceText("diegofalconc@hotmail.com"));
        onView(withId(R.id.username)).perform(replaceText("diegofalconc"));
        onView(withId(R.id.date_picker_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("1/2/2000")).check(doesNotExist());
    }
}