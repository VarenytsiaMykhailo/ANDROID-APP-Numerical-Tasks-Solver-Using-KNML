package com.github.varenytsiamykhailo.numericaltaskssolver

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
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
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        //assertThat(launcherPackage, notNullValue())
        waitWhileLoading(By.pkg(launcherPackage).depth(0))

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            PACKAGE
        ).apply {
            // Clear out any previous instances
            this!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        waitWhileLoading(By.pkg(PACKAGE).depth(0))
    }


    @Test
    fun testSimpsonMethodWorkflow() {
        checkUiElementsOnTheMainScreen()

        openIntegralMethodsScreen()
        checkUiElementsOnTheIntegralMethodsScreen()

        openSimpsonMethodInfoScreen()
        checkUiElementsOnTheSimpsonMethodInfoScreen()

        // Trying to scroll the scale of the text:
        val textScaleSeekBar: UiObject2 =
            getSeekBarAsUiObject2ByResId(SIMPSON_METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID)

        textScaleSeekBar.swipe(Direction.RIGHT, 0.8F)

        device.pressBack() // Back to the previous screen

        openInputIntegralDataScreen()

        // Trying enter the function:
        val functionEditText: UiObject2 = device.findObject(
            By.res(PACKAGE, INPUT_INTEGRAL_DATA_SCREEN_FUNCTION_EDIT_TEXT_RES_ID)
                .clazz("android.widget.EditText")
        )
        functionEditText.click()
        functionEditText.text = "x^2"
        device.pressBack() // Close keyboard

        // Trying to enable check box to form full solution:
        val formFullSolutionCheckBox: UiObject2 = device.findObject(
            By.res(PACKAGE, INPUT_INTEGRAL_DATA_SCREEN_FORM_FULL_SOLUTION_CHECK_BOX_RES_ID)
                .clazz("android.widget.CheckBox")
        )
        formFullSolutionCheckBox.click()

        openAnswerSolutionScreen()

        val descriptionTextView: UiObject2 =
            getTextViewAsUiObject2ByResId(ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID)

        // Check solution is correct:
        Assert.assertTrue(descriptionTextView.text.contains("Is successful: true"))
        Assert.assertTrue(descriptionTextView.text.contains("num of splits"))

        // Trying to scroll solution down:
        val verticalScrollView: UiObject2 = device.findObject(
            By.res(PACKAGE, ANSWER_SOLUTION_SCREEN_VERTICAL_SCROLL_VIEW_RES_ID)
                .clazz("android.widget.ScrollView")
        )
        verticalScrollView.scroll(Direction.DOWN, 1.0F)
        waitWhileLoading(By.text(EXPECTED_SOLUTION_RESULT))

        // Check answer is correct:
        Assert.assertTrue(
            descriptionTextView.text.contains(EXPECTED_SOLUTION_RESULT)
        )

        // Trying to scroll the scale of the solution text:
        val solutionTextScaleSeekBar: UiObject2 =
            getSeekBarAsUiObject2ByResId(ANSWER_SOLUTION_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID)
        solutionTextScaleSeekBar.swipe(Direction.RIGHT, 0.8F)
    }

    private fun openIntegralMethodsScreen() {
        val integralMethodsButton: UiObject2 =
            getButtonAsUiObject2(MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT)

        integralMethodsButton.click()

        waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID))
        waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID))
        waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID))
        waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT))
        waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT))
        waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT))
    }

    private fun openSimpsonMethodInfoScreen() {
        val simpsonMethodInfoImageButton: UiObject2 =
            getImageButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID)

        simpsonMethodInfoImageButton.click()

        waitWhileLoading(By.text(SIMPSON_METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT))
        waitWhileLoading(By.res(PACKAGE, SIMPSON_METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID))
        waitWhileLoading(By.res(PACKAGE, SIMPSON_METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID))
    }

    private fun openInputIntegralDataScreen() {
        val simpsonMethodButton: UiObject2 =
            getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT)

        simpsonMethodButton.click()

        waitWhileLoading(By.res(PACKAGE, INPUT_INTEGRAL_DATA_SCREEN_FUNCTION_EDIT_TEXT_RES_ID))
        waitWhileLoading(
            By.res(
                PACKAGE,
                INPUT_INTEGRAL_DATA_SCREEN_FORM_FULL_SOLUTION_CHECK_BOX_RES_ID
            )
        )
    }

    private fun openAnswerSolutionScreen() {
        val simpsonMethodButton: UiObject2 =
            getButtonAsUiObject2(INPUT_INTEGRAL_DATA_SCREEN_SOLVE_INTEGRAL_BUTTON_TEXT)

        simpsonMethodButton.click()

        waitWhileLoading(
            By.res(
                PACKAGE,
                ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID
            ).textContains("Is successful: true")
        )
        waitWhileLoading(
            By.res(
                PACKAGE,
                ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID
            ).textContains("num of splits")
        )
    }

    private fun checkUiElementsOnTheMainScreen() {
        // Integral methods:
        val integralMethodsButton =
            getButtonAsUiObject2(MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT)
        checkElementIsOnScreen(integralMethodsButton)
        val integralMethodsButtonVisibleBounds = integralMethodsButton.visibleBounds

        // System solving methods:
        val systemSolvingMethodsButton =
            getButtonAsUiObject2(MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT)
        checkElementIsOnScreen(systemSolvingMethodsButton)
        val systemSolvingMethodsButtonVisibleBounds = systemSolvingMethodsButton.visibleBounds

        // Optimization methods:
        val optimizationMethodsButton =
            getButtonAsUiObject2(MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT)
        checkElementIsOnScreen(optimizationMethodsButton)
        val optimizationMethodsButtonVisibleBounds = optimizationMethodsButton.visibleBounds

        // Check positions
        // Integral methods:
        Assert.assertTrue(
            integralMethodsButtonVisibleBounds.bottom
                    in 0..systemSolvingMethodsButtonVisibleBounds.top
        )

        // System solving methods:
        Assert.assertTrue(
            systemSolvingMethodsButtonVisibleBounds.bottom
                    in integralMethodsButtonVisibleBounds.bottom..optimizationMethodsButtonVisibleBounds.top
        )

        // Optimization methods:
        Assert.assertTrue(
            optimizationMethodsButtonVisibleBounds.bottom
                    in systemSolvingMethodsButtonVisibleBounds.bottom..device.displayHeight
        )
    }

    private fun checkUiElementsOnTheIntegralMethodsScreen() {
        val chooseTheMethodTextView: UiObject2 =
            getTextViewAsUiObject2(INTEGRAL_METHODS_SCREEN_CHOOSE_THE_METHOD_TEXT_VIEW_TEXT)

        checkElementIsOnScreen(chooseTheMethodTextView)
        val chooseTheMethodTextViewVisibleBounds = chooseTheMethodTextView.visibleBounds

        // Rectangle method:
        val rectangleMethodButton =
            getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT)
        checkElementIsOnScreen(rectangleMethodButton)
        val rectangleMethodButtonVisibleBounds = rectangleMethodButton.visibleBounds

        val rectangleMethodInfoImageButton: UiObject2 =
            getImageButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID)
        checkElementIsOnScreen(rectangleMethodInfoImageButton)
        val rectangleMethodInfoImageButtonVisibleBounds =
            rectangleMethodInfoImageButton.visibleBounds

        // Trapezoid method:
        val trapezoidMethodButton =
            getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT)
        checkElementIsOnScreen(trapezoidMethodButton)
        val trapezoidMethodButtonVisibleBounds = trapezoidMethodButton.visibleBounds

        val trapezoidMethodInfoImageButton: UiObject2 =
            getImageButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID)
        checkElementIsOnScreen(trapezoidMethodInfoImageButton)
        val trapezoidMethodInfoImageButtonVisibleBounds =
            trapezoidMethodInfoImageButton.visibleBounds

        // Simpson method:
        val simpsonMethodButton =
            getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT)
        checkElementIsOnScreen(simpsonMethodButton)
        val simpsonMethodButtonVisibleBounds = simpsonMethodButton.visibleBounds

        val simpsonMethodInfoImageButton: UiObject2 =
            getImageButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID)
        checkElementIsOnScreen(simpsonMethodInfoImageButton)
        val simpsonMethodInfoImageButtonVisibleBounds = simpsonMethodInfoImageButton.visibleBounds

        // Check positions:
        // Choose the method:
        Assert.assertTrue(
            chooseTheMethodTextViewVisibleBounds.bottom
                    in 0..rectangleMethodButtonVisibleBounds.top
        )

        // Rectangle method:
        Assert.assertTrue(
            rectangleMethodInfoImageButtonVisibleBounds.left
                    in 0..rectangleMethodButtonVisibleBounds.left
        )
        Assert.assertTrue(
            rectangleMethodButtonVisibleBounds.left
                    in rectangleMethodInfoImageButtonVisibleBounds.right..device.displayWidth
        )
        Assert.assertTrue(
            rectangleMethodButtonVisibleBounds.bottom
                    in chooseTheMethodTextViewVisibleBounds.bottom..trapezoidMethodButtonVisibleBounds.top
        )

        // Trapezoid method:
        Assert.assertTrue(
            trapezoidMethodInfoImageButtonVisibleBounds.left
                    in 0..trapezoidMethodButtonVisibleBounds.left
        )
        Assert.assertTrue(
            trapezoidMethodButtonVisibleBounds.left
                    in trapezoidMethodInfoImageButtonVisibleBounds.right..device.displayWidth
        )
        Assert.assertTrue(
            trapezoidMethodButtonVisibleBounds.bottom
                    in rectangleMethodButtonVisibleBounds.bottom..simpsonMethodButtonVisibleBounds.top
        )

        // Simpson method:
        Assert.assertTrue(
            simpsonMethodInfoImageButtonVisibleBounds.left
                    in 0..simpsonMethodButtonVisibleBounds.left
        )
        Assert.assertTrue(
            simpsonMethodButtonVisibleBounds.left
                    in simpsonMethodInfoImageButtonVisibleBounds.right..device.displayWidth
        )
        Assert.assertTrue(
            simpsonMethodButtonVisibleBounds.bottom
                    in trapezoidMethodButtonVisibleBounds.bottom..device.displayHeight
        )

    }

    private fun checkUiElementsOnTheSimpsonMethodInfoScreen() {
        val methodDescriptionTextView: UiObject2 =
            getTextViewAsUiObject2(SIMPSON_METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT)

        checkElementIsOnScreen(methodDescriptionTextView)
        val methodDescriptionTextViewVisibleBounds = methodDescriptionTextView.visibleBounds

        val descriptionTextView: UiObject2 =
            getTextViewAsUiObject2ByResId(SIMPSON_METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID)

        checkElementIsOnScreen(methodDescriptionTextView)
        val descriptionTextViewVisibleBounds = descriptionTextView.visibleBounds

        val textScaleSeekBar: UiObject2 =
            getSeekBarAsUiObject2ByResId(SIMPSON_METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID)
        checkElementIsOnScreen(textScaleSeekBar)
        val textScaleSeekBarVisibleBounds = textScaleSeekBar.visibleBounds

        // Check positions:
        // Method description:
        Assert.assertTrue(
            methodDescriptionTextViewVisibleBounds.bottom
                    in 0..descriptionTextViewVisibleBounds.top
        )

        // Description:
        Assert.assertTrue(
            descriptionTextViewVisibleBounds.bottom
                    in methodDescriptionTextViewVisibleBounds.bottom..textScaleSeekBarVisibleBounds.top
        )

        // TextScale SeekBar:
        Assert.assertTrue(
            textScaleSeekBarVisibleBounds.bottom
                    in descriptionTextViewVisibleBounds.bottom..device.displayHeight
        )
    }

    private fun getButtonAsUiObject2(buttonText: String): UiObject2 {
        return device.findObject(
            By.text(buttonText)
                .clazz("android.widget.Button")
        )
    }

    private fun getImageButtonAsUiObject2(imageButtonResId: String): UiObject2 {
        return device.findObject(
            By.res(PACKAGE, imageButtonResId)
                .clazz("android.widget.ImageButton")
        )
    }

    private fun getTextViewAsUiObject2(textViewText: String): UiObject2 {
        return device.findObject(
            By.text(textViewText)
                .clazz("android.widget.TextView")
        )
    }

    private fun getTextViewAsUiObject2ByResId(textViewResId: String): UiObject2 {
        return device.findObject(
            By.res(PACKAGE, textViewResId)
                .clazz("android.widget.TextView")
        )
    }

    private fun getSeekBarAsUiObject2ByResId(seekBarResId: String): UiObject2 {
        return device.findObject(
            By.res(PACKAGE, seekBarResId)
                .clazz("android.widget.SeekBar")
        )
    }

    private fun checkElementIsOnScreen(uiObject2: UiObject2) {
        val visibleCenter = uiObject2.visibleCenter
        Assert.assertTrue(visibleCenter.x in 0..device.displayWidth)
        Assert.assertTrue(visibleCenter.y in 0..device.displayHeight)
    }

    private fun waitWhileLoading(bySelector: BySelector) {
        val result = device.wait(Until.hasObject(bySelector), WAIT_TIMEOUT)
        Assert.assertNotNull(result)
    }

    companion object {
        private val PACKAGE =
            InstrumentationRegistry.getInstrumentation().targetContext.packageName

        private const val WAIT_TIMEOUT = 3000L

        private const val EXPECTED_SOLUTION_RESULT = "Solution result: 41.3348"

        // Main screen values:
        private const val MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT = "INTEGRAL METHODS"
        private const val MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT = "SYSTEM SOLVING METHODS"
        private const val MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT = "OPTIMIZATION METHODS"

        // Integral methods screen values:
        private const val INTEGRAL_METHODS_SCREEN_CHOOSE_THE_METHOD_TEXT_VIEW_TEXT =
            "Choose the method:"

        private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID =
            "rectangleMethodInfo_Button"
        private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID =
            "trapezoidMethodInfo_Button"
        private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID =
            "simpsonMethodInfo_Button"

        private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT = "RECTANGLE METHOD"
        private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT = "TRAPEZOID METHOD"
        private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT = "SIMPSON METHOD"

        // Simpson method info screen values:
        private const val SIMPSON_METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT =
            "Method description:"
        private const val SIMPSON_METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID =
            "description_TextView"
        private const val SIMPSON_METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID =
            "textScale_SeekBar"

        // Input integral data screen values:
        private const val INPUT_INTEGRAL_DATA_SCREEN_FUNCTION_EDIT_TEXT_RES_ID =
            "function_EditText"
        private const val INPUT_INTEGRAL_DATA_SCREEN_FORM_FULL_SOLUTION_CHECK_BOX_RES_ID =
            "formFullSolution_CheckBox"
        private const val INPUT_INTEGRAL_DATA_SCREEN_SOLVE_INTEGRAL_BUTTON_TEXT = "SOLVE INTEGRAL"

        // Answer solution screen values:
        private const val ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID =
            "solution_TextView"
        private const val ANSWER_SOLUTION_SCREEN_VERTICAL_SCROLL_VIEW_RES_ID =
            "vertical_ScrollView"
        private const val ANSWER_SOLUTION_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID =
            "textScale_SeekBar"
    }
}