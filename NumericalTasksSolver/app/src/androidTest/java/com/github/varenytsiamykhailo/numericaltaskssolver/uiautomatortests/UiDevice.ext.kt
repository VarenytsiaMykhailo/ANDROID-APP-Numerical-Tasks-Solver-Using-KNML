package com.github.varenytsiamykhailo.numericaltaskssolver

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert

private const val WAIT_TIMEOUT = 3000L

internal fun setupUiDevice(): UiDevice {
    // Initialize UiDevice instance
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    // Start from the home screen
    device.pressHome()

    // Wait for launcher
    val launcherPackage: String = device.launcherPackageName
    //assertThat(launcherPackage, notNullValue())
    device.waitWhileLoading(By.pkg(launcherPackage).depth(0))

    // Launch the app
    val context = ApplicationProvider.getApplicationContext<Context>()
    val intent = context.packageManager.getLaunchIntentForPackage(
        pkg
    ).apply {
        // Clear out any previous instances
        this!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    context.startActivity(intent)

    // Wait for the app to appear
    device.waitWhileLoading(By.pkg(pkg).depth(0))

    return device
}

internal fun UiDevice.getButtonAsUiObject2(buttonText: String): UiObject2 {
    return this.findObject(
        By.text(buttonText)
            .clazz("android.widget.Button")
    )
}

internal fun UiDevice.getImageButtonAsUiObject2(imageButtonResId: String): UiObject2 {
    return this.findObject(
        By.res(pkg, imageButtonResId)
            .clazz("android.widget.ImageButton")
    )
}

internal fun UiDevice.getTextViewAsUiObject2(textViewText: String): UiObject2 {
    return this.findObject(
        By.text(textViewText)
            .clazz("android.widget.TextView")
    )
}

internal fun UiDevice.getTextViewAsUiObject2ByResId(textViewResId: String): UiObject2 {
    return this.findObject(
        By.res(pkg, textViewResId)
            .clazz("android.widget.TextView")
    )
}

internal fun UiDevice.getSeekBarAsUiObject2ByResId(seekBarResId: String): UiObject2 {
    return this.findObject(
        By.res(pkg, seekBarResId)
            .clazz("android.widget.SeekBar")
    )
}

internal fun UiDevice.checkElementIsOnScreen(uiObject2: UiObject2) {
    val visibleCenter = uiObject2.visibleCenter
    Assert.assertTrue(visibleCenter.x in 0..this.displayWidth)
    Assert.assertTrue(visibleCenter.y in 0..this.displayHeight)
}

internal fun UiDevice.waitWhileLoading(bySelector: BySelector) {
    val result = this.wait(Until.hasObject(bySelector), WAIT_TIMEOUT)
    Assert.assertNotNull(result)
}
