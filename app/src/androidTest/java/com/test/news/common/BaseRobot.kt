package com.test.news.common

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn

open class BaseTestRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(resId))
            .perform(ViewActions.clearText(), ViewActions.typeText(text))

    fun tapOnButton(resId: Int) {
        clickOn(resId)
    }

    fun viewIsVisible(resId: Int) {
        assertDisplayed(resId)
    }

    fun sleep(millis: Long = 350) = apply {
        Thread.sleep(millis)
    }

    fun checkToolbarTitleString(expectedText: String): ViewInteraction =
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.isAssignableFrom(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar::class.java))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedText)))


    fun pressItemInList(listRes: Int, position: Int): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(listRes))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    ViewActions.click()
                )
            )

    fun stringIsVisibleIsDescendant(text: String, resId: Int): ViewInteraction =
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withText(text),
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(resId))
            )
        )

    fun imageIsDisplayed(resId: Int) {
        assertDisplayed(resId)
    }

    companion object {
        const val VALID_USER_NAME = "user1"
        const val VALID_USER_PASSWORD = "password"
        const val INVALID_USER_NAME = "invalidName"
    }
}