package br.heitor.easyinvest.views.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.heitor.easyinvest.R;
import utils.NestedScrollViewScrollToAction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@SuppressWarnings("FieldCanBeLocal")
@RunWith(AndroidJUnit4.class)
public class SendMessageTest {

    private String nameToTest = "Heitor Polizeli Rodrigues";
    private String emailToTest = "heitorprodrigues@gmail.com";
    private String telephoneToTest = "11955707107";
    private String telephoneFormatted = "11 95570-7107";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sendMessageTest() {
        ViewInteraction fontButtonView = onView(
                allOf(withId(R.id.btnNavContact), withText(R.string.contact),
                        withParent(withId(R.id.navigation)),
                        isDisplayed()));
        fontButtonView.perform(click());

        ViewInteraction fontEditView = onView(
                allOf(withId(R.id.txt_name), isDisplayed()));
        fontEditView.perform(typeText(nameToTest), closeSoftKeyboard());

        ViewInteraction fontEditView2 = onView(
                allOf(withId(R.id.txt_name), withText(nameToTest), isDisplayed()));
        fontEditView2.perform(pressImeActionButton());

        ViewInteraction fontEditView3 = onView(
                allOf(withId(R.id.txt_email), isDisplayed()));
        fontEditView3.perform(typeText(emailToTest), closeSoftKeyboard());

        ViewInteraction fontEditView4 = onView(
                allOf(withId(R.id.txt_email), withText(emailToTest), isDisplayed()));
        fontEditView4.perform(pressImeActionButton());

        ViewInteraction fontEditView5 = onView(
                allOf(withId(R.id.txt_telephone), isDisplayed()));
        fontEditView5.perform(typeText(telephoneToTest), closeSoftKeyboard());

        ViewInteraction fontEditView6 = onView(
                allOf(withId(R.id.txt_telephone), withText(telephoneFormatted), isDisplayed()));
        fontEditView6.perform(pressImeActionButton());

        onView(withId(R.id.btn_send)).perform(new NestedScrollViewScrollToAction());

        ViewInteraction fontCheckBoxView = onView(
                allOf(withId(R.id.btn_checkbox), withText(R.string.info_add_email_address), isDisplayed()));
        fontCheckBoxView.perform(click());

        ViewInteraction fontButtonView2 = onView(
                allOf(withId(R.id.btn_send), withText(R.string.action_send), isDisplayed()));
        fontButtonView2.perform(click());
    }
}
