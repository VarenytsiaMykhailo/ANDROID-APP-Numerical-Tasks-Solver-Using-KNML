package com.github.varenytsiamykhailo.numericaltaskssolver.uiautomatortests.integralsolvingmethods

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.github.varenytsiamykhailo.numericaltaskssolver.*

// Main screen values:
private const val MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT = "INTEGRAL METHODS"
private const val MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT = "SYSTEM SOLVING METHODS"
private const val MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT = "OPTIMIZATION METHODS"

// Integral methods screen values:
private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID =
    "rectangleMethodInfo_Button"
private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID =
    "trapezoidMethodInfo_Button"
private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID =
    "simpsonMethodInfo_Button"

private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT = "RECTANGLE METHOD"
private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT = "TRAPEZOID METHOD"
private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT = "SIMPSON METHOD"

// Method info screen values:
private const val METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT =
    "Method description:"
private const val METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID =
    "description_TextView"
private const val METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID =
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

internal fun UiDevice.openIntegralMethodsScreen() {
    val integralMethodsButton: UiObject2 =
        this.getButtonAsUiObject2(MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT)

    integralMethodsButton.click()

    this.waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID))
    this.waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID))
    this.waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID))
    this.waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT))
    this.waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT))
    this.waitWhileLoading(By.text(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT))
}

internal fun UiDevice.openMethodInfoScreen(buttonToOpenResId: String) {
    val simpsonMethodInfoImageButton: UiObject2 =
        this.getImageButtonAsUiObject2(buttonToOpenResId)

    simpsonMethodInfoImageButton.click()

    this.waitWhileLoading(By.text(METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT))
    this.waitWhileLoading(By.res(pkg, METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID))
    this.waitWhileLoading(By.res(pkg, METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID))
}

internal fun UiDevice.openInputIntegralDataScreen(buttonText: String) {
    val simpsonMethodButton: UiObject2 =
        this.getButtonAsUiObject2(buttonText)

    simpsonMethodButton.click()

    this.waitWhileLoading(By.res(pkg, INPUT_INTEGRAL_DATA_SCREEN_FUNCTION_EDIT_TEXT_RES_ID))
    this.waitWhileLoading(
        By.res(pkg, INPUT_INTEGRAL_DATA_SCREEN_FORM_FULL_SOLUTION_CHECK_BOX_RES_ID)
    )
}

internal fun UiDevice.openAnswerSolutionScreen() {
    val simpsonMethodButton: UiObject2 =
        this.getButtonAsUiObject2(INPUT_INTEGRAL_DATA_SCREEN_SOLVE_INTEGRAL_BUTTON_TEXT)

    simpsonMethodButton.click()

    this.waitWhileLoading(
        By.res(pkg, ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID)
            .textContains("Is successful: true")
    )
    this.waitWhileLoading(
        By.res(pkg, ANSWER_SOLUTION_SCREEN_SOLUTION_TEXT_VIEW_RES_ID)
            .textContains("num of splits")
    )
}