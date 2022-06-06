package com.example.espressodemo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
public class ListSelectTest {

    @Rule
    public ActivityTestRule<ListActivity> activityRule
            = new ActivityTestRule<>(ListActivity.class);

    @Test
    public void checkSelectItem() {
        onView(withId(R.id.item_list_exp_tv)).check(matches(withText(startsWith("Item"))));
        int index = 4;
        onData((is(instanceOf(String.class))))
                .inAdapterView(withId(R.id.activity_list_exp_lv))
                .atPosition(index)
                .onChildView(withId(R.id.item_list_exp_tv))
                .perform(click()).check(matches(withText("Item" + index)));
        onView(withId(R.id.activity_main_show_input_tv))
                .check(matches(withText("Item" + index)));
    }
}
