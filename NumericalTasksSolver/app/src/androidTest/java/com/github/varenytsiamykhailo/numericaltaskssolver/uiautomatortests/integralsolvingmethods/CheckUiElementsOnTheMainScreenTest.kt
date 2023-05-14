package com.github.varenytsiamykhailo.numericaltaskssolver.uiautomatortests.integralsolvingmethods

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.UiDevice
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.github.varenytsiamykhailo.numericaltaskssolver.*

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class CheckUiElementsOnTheMainScreenTest {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        device = setupUiDevice()
    }

    @Test
    fun testUiElementsOnTheMainScreen() {
        checkUiElementsOnTheMainScreen()
    }

    private fun checkUiElementsOnTheMainScreen() {
        // Integral methods:
        val integralMethodsButton =
            device.getButtonAsUiObject2(MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT)
        device.checkElementIsOnScreen(integralMethodsButton)
        val integralMethodsButtonVisibleBounds = integralMethodsButton.visibleBounds

        // System solving methods:
        val systemSolvingMethodsButton =
            device.getButtonAsUiObject2(MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT)
        device.checkElementIsOnScreen(systemSolvingMethodsButton)
        val systemSolvingMethodsButtonVisibleBounds = systemSolvingMethodsButton.visibleBounds

        // Optimization methods:
        val optimizationMethodsButton =
            device.getButtonAsUiObject2(MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT)
        device.checkElementIsOnScreen(optimizationMethodsButton)
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

    companion object {
        // Main screen values:
        private const val MAIN_SCREEN_INTEGRAL_METHODS_BUTTON_TEXT = "INTEGRAL METHODS"

        private const val MAIN_SCREEN_SYSTEM_SOLVING_METHODS_BUTTON_TEXT = "SYSTEM SOLVING METHODS"

        private const val MAIN_SCREEN_OPTIMIZATION_METHODS_BUTTON_TEXT = "OPTIMIZATION METHODS"

    }

}