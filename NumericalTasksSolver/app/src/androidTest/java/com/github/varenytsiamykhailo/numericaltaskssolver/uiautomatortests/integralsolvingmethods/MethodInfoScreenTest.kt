package com.github.varenytsiamykhailo.numericaltaskssolver.uiautomatortests.integralsolvingmethods

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.github.varenytsiamykhailo.numericaltaskssolver.*

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class MethodInfoScreenTest {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        device = setupUiDevice()
    }

    @Test
    fun testMethodInfoScreenWorkflow() {
        device.openIntegralMethodsScreen()

        checkMethodInfoScreen(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID)
        checkMethodInfoScreen(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID)
        checkMethodInfoScreen(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID)
    }

    private fun checkMethodInfoScreen(buttonToOpenResId: String) {
        device.openMethodInfoScreen(buttonToOpenResId)
        checkUiElementsOnTheMethodInfoScreen()

        // Trying to scroll the scale of the text:
        val textScaleSeekBar: UiObject2 =
            device.getSeekBarAsUiObject2ByResId(
                METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID
            )
        textScaleSeekBar.swipe(Direction.RIGHT, 0.8F)

        device.pressBack() // Back to the previous screen
    }

    private fun checkUiElementsOnTheMethodInfoScreen() {
        val methodDescriptionTextView: UiObject2 =
            device.getTextViewAsUiObject2(
                METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT
            )

        device.checkElementIsOnScreen(methodDescriptionTextView)
        val methodDescriptionTextViewVisibleBounds = methodDescriptionTextView.visibleBounds

        val descriptionTextView: UiObject2 =
            device.getTextViewAsUiObject2ByResId(
                METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID
            )

        device.checkElementIsOnScreen(methodDescriptionTextView)
        val descriptionTextViewVisibleBounds = descriptionTextView.visibleBounds

        val textScaleSeekBar: UiObject2 =
            device.getSeekBarAsUiObject2ByResId(
                METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID
            )
        device.checkElementIsOnScreen(textScaleSeekBar)
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

    companion object {
        // Integral methods screen values:
        private const val INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID =
            "rectangleMethodInfo_Button"
        private const val INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID =
            "trapezoidMethodInfo_Button"
        private const val INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID =
            "simpsonMethodInfo_Button"

        // Method info screen values:
        private const val METHOD_INFO_SCREEN_METHOD_DESCRIPTION_TEXT_VIEW_TEXT =
            "Method description:"
        private const val METHOD_INFO_SCREEN_DESCRIPTION_TEXT_VIEW_RES_ID =
            "description_TextView"
        private const val METHOD_INFO_SCREEN_TEXT_SCALE_SEEK_BAR_RES_ID =
            "textScale_SeekBar"
    }
}