package com.github.varenytsiamykhailo.numericaltaskssolver.integralsolvingmethods

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import com.github.varenytsiamykhailo.numericaltaskssolver.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class SimpsonMethodTest {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        device = setupUiDevice()
    }

    @Test
    fun testSimpsonMethodWorkflow() {
        device.openIntegralMethodsScreen()
        device.openInputIntegralDataScreen(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT)

        // Trying enter the function:
        val functionEditText: UiObject2 = device.findObject(
            By.res(pkg, INPUT_INTEGRAL_DATA_SCREEN_FUNCTION_EDIT_TEXT_RES_ID)
                .clazz("android.widget.EditText")
        )
        functionEditText.click()
        functionEditText.text = "x^2"
        device.pressBack() // Close keyboard

        // Trying to enable check box to form full solution:
        val formFullSolutionCheckBox: UiObject2 = device.findObject(
            By.res(pkg, INPUT_INTEGRAL_DATA_SCREEN_FORM_FULL_SOLUTION_CHECK_BOX_RES_ID)
                .clazz("android.widget.CheckBox")
        )
        formFullSolutionCheckBox.click()

        device.openAnswerSolutionScreen()

        val descriptionTextView: UiObject2 =
            device.getTextViewAsUiObject2ByResId(ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID)

        // Check solution is correct:
        Assert.assertTrue(descriptionTextView.text.contains("Is successful: true"))
        Assert.assertTrue(descriptionTextView.text.contains("num of splits"))

        // Trying to scroll solution down:
        val verticalScrollView: UiObject2 = device.findObject(
            By.res(pkg, ANSWER_SOLUTION_SCREEN_VERTICAL_SCROLL_VIEW_RES_ID)
                .clazz("android.widget.ScrollView")
        )
        verticalScrollView.scroll(Direction.DOWN, 1.0F)
        device.waitWhileLoading(By.text(EXPECTED_SOLUTION_RESULT))

        // Check answer is correct:
        Assert.assertTrue(
            descriptionTextView.text.contains(EXPECTED_SOLUTION_RESULT)
        )

        // Trying to scroll the scale of the solution text:
        val solutionTextScaleSeekBar: UiObject2 =
            device.getSeekBarAsUiObject2ByResId(ANSWER_SOLUTION_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID)
        solutionTextScaleSeekBar.swipe(Direction.RIGHT, 0.8F)
    }

    companion object {

        private const val EXPECTED_SOLUTION_RESULT = "Solution result: 41.3348"

        // Integral methods screen values:
        private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT = "RECTANGLE METHOD"
        private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT = "TRAPEZOID METHOD"
        private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT = "SIMPSON METHOD"

        // Input integral data screen values:
        private const val INPUT_INTEGRAL_DATA_SCREEN_FUNCTION_EDIT_TEXT_RES_ID =
            "function_EditText"
        private const val INPUT_INTEGRAL_DATA_SCREEN_FORM_FULL_SOLUTION_CHECK_BOX_RES_ID =
            "formFullSolution_CheckBox"

        // Answer solution screen values:
        private const val ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID =
            "solution_TextView"
        private const val ANSWER_SOLUTION_SCREEN_VERTICAL_SCROLL_VIEW_RES_ID =
            "vertical_ScrollView"
        private const val ANSWER_SOLUTION_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID =
            "textScale_SeekBar"
    }
}