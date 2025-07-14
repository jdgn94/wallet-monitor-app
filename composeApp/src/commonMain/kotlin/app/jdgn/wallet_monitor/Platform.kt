package app.jdgn.wallet_monitor

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

enum class Platform {
    ANDROID,
    IOS
}

expect val currentPlatform: Platform

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp
