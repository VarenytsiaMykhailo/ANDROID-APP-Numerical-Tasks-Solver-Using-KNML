package com.github.varenytsiamykhailo.numericaltaskssolver.espressotests.integralsolvingmethods

import android.view.View
import android.widget.SeekBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
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
class SimpsonMethodTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSimpsonMethodWorkflow() {
        onView(withId(R.id.mainActivity_IntegralMethods_Button))
            .perform(click())

        onView(withId(R.id.simpsonMethod_Button))
            .perform(click())

        // Trying enter the function:
        onView(withId(R.id.function_EditText))
            .perform(typeText("x^2"), closeSoftKeyboard())

        onView(withId(R.id.function_EditText))
            .check(matches(withText("x^2")))

        // Trying to enable check box to form full solution:
        onView(withId(R.id.formFullSolution_CheckBox))
            .check(matches(isNotChecked()))
            .perform(click())
            .check(matches(isChecked()))

        // Go to the solution screen
        onView(withId(R.id.solveIntegral_Button))
            .perform(click())

        // Check solution is correct:
        onView(withId(R.id.solution_TextView))
            .check(matches(withSubstring("Is successful: true")))
            .check(matches(withSubstring("num of splits")))

        // Trying to scroll solution down:
        // Dont work because:
        // Espresso doesn't work well with animations due to the visual state delays they introduce.
        // Can be fixed: https://stackoverflow.com/questions/44005338/android-espresso-performexception
        /*
        onView(withId(R.id.solution_TextView))
            .perform(scrollTo(), click())
         */

        // Check answer is correct:
        onView(withId(R.id.solution_TextView))
            .check(matches(withSubstring(EXPECTED_SOLUTION_RESULT)))

        // Trying to scroll the scale of the solution text:
        onView(withId(R.id.textScale_SeekBar))
            .perform(setProgress(90))
    }

    private fun setProgress(progress: Int): ViewAction? {
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
        private const val EXPECTED_SOLUTION_RESULT = "Solution result: 41.3348"
    }

}