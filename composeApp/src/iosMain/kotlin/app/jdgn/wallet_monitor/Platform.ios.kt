package app.jdgn.wallet_monitor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIScreen
import platform.CoreGraphics.CGRectGetWidth
import platform.CoreGraphics.CGRectGetHeight

actual val currentPlatform = Platform.IOS

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getScreenWidth(): Dp {
    return remember {
        val screen = UIScreen.mainScreen
        CGRectGetWidth(screen.bounds).toFloat().dp
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getScreenHeight(): Dp {
    return remember {
        val screen = UIScreen.mainScreen
        CGRectGetHeight(screen.bounds).toFloat().dp
    }
}
