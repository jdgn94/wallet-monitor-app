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

interface Cancellable {
    fun cancel()
}

expect val currentPlatform: Platform

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp

expect class OrientationManager {
    fun getOrientation(): Orientation
    fun observeOrientation(onChange: (Orientation) -> Unit): Cancellable
}

