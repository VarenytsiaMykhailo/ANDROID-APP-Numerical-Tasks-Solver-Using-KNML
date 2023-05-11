package com.github.varenytsiamykhailo.numericaltaskssolver.integralsolvingmethods

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert
import com.github.varenytsiamykhailo.numericaltaskssolver.*

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class CheckUiElementsOnTheIntegralMethodsScreenTest {
    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        device = setupUiDevice()
    }

    @Test
    fun testUiElementsOnTheIntegralMethodsScreen() {
        device.openIntegralMethodsScreen()
        checkUiElementsOnTheIntegralMethodsScreen()
    }

    private fun checkUiElementsOnTheIntegralMethodsScreen() {
        val chooseTheMethodTextView: UiObject2 =
            device.getTextViewAsUiObject2(INTEGRAL_METHODS_SCREEN_CHOOSE_THE_METHOD_TEXT_VIEW_TEXT)

        device.checkElementIsOnScreen(chooseTheMethodTextView)
        val chooseTheMethodTextViewVisibleBounds = chooseTheMethodTextView.visibleBounds

        // Rectangle method:
        val rectangleMethodButton =
            device.getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_BUTTON_TEXT)
        device.checkElementIsOnScreen(rectangleMethodButton)
        val rectangleMethodButtonVisibleBounds = rectangleMethodButton.visibleBounds

        val rectangleMethodInfoImageButton: UiObject2 =
            device.getImageButtonAsUiObject2(
                INTEGRAL_METHODS_SCREEN_RECTANGLE_METHOD_INFO_BUTTON_RES_ID
            )
        device.checkElementIsOnScreen(rectangleMethodInfoImageButton)
        val rectangleMethodInfoImageButtonVisibleBounds =
            rectangleMethodInfoImageButton.visibleBounds

        // Trapezoid method:
        val trapezoidMethodButton =
            device.getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_BUTTON_TEXT)
        device.checkElementIsOnScreen(trapezoidMethodButton)
        val trapezoidMethodButtonVisibleBounds = trapezoidMethodButton.visibleBounds

        val trapezoidMethodInfoImageButton: UiObject2 =
            device.getImageButtonAsUiObject2(
                INTEGRAL_METHODS_SCREEN_TRAPEZOID_METHOD_INFO_BUTTON_RES_ID
            )
        device.checkElementIsOnScreen(trapezoidMethodInfoImageButton)
        val trapezoidMethodInfoImageButtonVisibleBounds =
            trapezoidMethodInfoImageButton.visibleBounds

        // Simpson method:
        val simpsonMethodButton =
            device.getButtonAsUiObject2(INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_BUTTON_TEXT)
        device.checkElementIsOnScreen(simpsonMethodButton)
        val simpsonMethodButtonVisibleBounds = simpsonMethodButton.visibleBounds

        val simpsonMethodInfoImageButton: UiObject2 =
            device.getImageButtonAsUiObject2(
                INTEGRAL_METHODS_SCREEN_SIMPSON_METHOD_INFO_BUTTON_RES_ID
            )
        device.checkElementIsOnScreen(simpsonMethodInfoImageButton)
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

    companion object {
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
    }
}