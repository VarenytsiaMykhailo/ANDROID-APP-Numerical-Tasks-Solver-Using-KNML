package com.github.varenytsiamykhailo.numericaltaskssolver.espressotests.integralsolvingmethods

import android.view.View
import android.widget.SeekBar
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.varenytsiamykhailo.numericaltaskssolver.MainActivity
import com.github.varenytsiamykhailo.numericaltaskssolver.R
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MethodInfoScreenTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMethodInfoScreenWorkflow() {
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .perform(click())

        checkMethodInfoScreen("rectangle")
        checkMethodInfoScreen("trapezoid")
        checkMethodInfoScreen("simpson")
    }

    private fun checkMethodInfoScreen(methodType: String) {
        onView(
            withId(
                when (methodType) {
                    "rectangle" -> R.id.rectangleMethodInfo_Button
                    "trapezoid" -> R.id.trapezoidMethodInfo_Button
                    "simpson" -> R.id.simpsonMethodInfo_Button
                    else -> throw java.lang.IllegalArgumentException("Incorrect method type: $methodType")
                }
            )
        )
            .perform(click())

        onView(withId(R.id.methodDescriptionTitle_TextView))
            .check(matches(isDisplayed()))
            .check(matches(withText(METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT)))

        onView(withId(R.id.description_TextView))
            .check(matches(isDisplayed()))
            .check(
                matches(
                    withText(
                        when (methodType) {
                            "rectangle" -> R.string.rectangle_method_description
                            "trapezoid" -> R.string.trapezoid_method_description
                            "simpson" -> R.string.simpson_method_description
                            else -> throw java.lang.IllegalArgumentException("Incorrect method type: $methodType")
                        }
                    )
                )
            )

        onView(withId(R.id.textScale_SeekBar))
            .check(matches(isDisplayed()))
            .perform(setProgress(90))

        Espresso.pressBack()
    }

    private fun setProgress(progress: Int): ViewAction {
        return object : ViewAction {
            override fun perform(uiController: UiController?, view: View) {
                val seekBar = view as SeekBar
                seekBar.progress = progress
            }

            override fun getDescription(): String {
                return "Set a progress on a SeekBar"
            }

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(SeekBar::class.java)
            }
        }
    }

    companion object {
        // Method info screen values:
        private const val METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT =
            "Method description:"
        private const val METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID =
            "description_TextView"
        private const val METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID =
            "textScale_SeekBar"
    }
}