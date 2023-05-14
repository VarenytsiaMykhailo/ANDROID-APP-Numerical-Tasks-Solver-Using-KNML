package com.github.varenytsiamykhailo.numericaltaskssolver.espressotests.integralsolvingmethods

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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
class CheckUiElementsOnTheIntegralMethodsScreenTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUiElementsOnTheMainScreen() {
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .perform(ViewActions.click())

        checkUiElementsOnTheIntegralMethodsScreen()
    }

    private fun checkUiElementsOnTheIntegralMethodsScreen() {
        onView(withId(R.id.rectangleMethodInfo_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rectangleMethod_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rectangleMethod_Button))
            .check(matches(withText(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT)))

        onView(withId(R.id.trapezoidMethodInfo_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.trapezoidMethod_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.trapezoidMethod_Button))
            .check(matches(withText(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT)))

        onView(withId(R.id.simpsonMethodInfo_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.simpsonMethod_Button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.simpsonMethod_Button))
            .check(matches(withText(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT)))
    }

    companion object {
        // Integral methods screen values:
        private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT = "RECTANGLE METHOD"
        private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT = "TRAPEZOID METHOD"
        private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT = "SIMPSON METHOD"
    }
}