package com.github.varenytsiamykhailo.numericaltaskssolver.espressotests.integralsolvingmethods

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.varenytsiamykhailo.numericaltaskssolver.MainActivity
import com.github.varenytsiamykhailo.numericaltaskssolver.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class CheckUiElementsOnTheMainScreenTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUiElementsOnTheMainScreen() {
        checkUiElementsOnTheMainScreen()
    }

    private fun checkUiElementsOnTheMainScreen() {
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .check(matches(isClickable()))
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .check(matches(withText(MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT)))

        onView(withId(R.id.mainActivity_SystemSolvingMethods_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .check(matches(isClickable()))
        onView(withId(R.id.mainActivity_SystemSolvingMethods_Button))
            .check(matches(withText(MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT)))

        onView(withId(R.id.mainActivity_OptimizationMethods_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .check(matches(isClickable()))
        onView(withId(R.id.mainActivity_OptimizationMethods_Button))
            .check(matches(withText(MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT)))
    }

    companion object {
        // Main screen values:
        private const val MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT = "INTEGRAL METHODS"
        private const val MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT = "SYSTEM SOLVING METHODS"
        private const val MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT = "OPTIMIZATION METHODS"
    }
}