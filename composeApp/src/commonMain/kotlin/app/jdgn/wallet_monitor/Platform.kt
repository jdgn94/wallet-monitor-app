package app.jdgn.wallet_monitor

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

enum class Platform {
    ANDROID,
    IOS
}

enum class Orientation {
    PORTRAIT,
    LANDSCAPE
}

enum class ToastDuration {
    SHORT,
    LONG
}

interface Cancellable {
    fun cancel()
}

expect val currentPlatform: Platform

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ToastManager {
    fun showToast(message: String, duration: ToastDuration = ToastDuration.SHORT)
}

expect fun getSystemLanguage(): String

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class OrientationManager {
    fun getOrientation(): Orientation
    fun observeOrientation(onChange: (Orientation) -> Unit): Cancellable
}

