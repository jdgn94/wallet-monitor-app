package app.jdgn.wallet_monitor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIScreen
import platform.CoreGraphics.CGRectGetWidth
import platform.CoreGraphics.CGRectGetHeight
import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceOrientation
import platform.UIKit.UIDeviceOrientationDidChangeNotification
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.darwin.NSObject

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

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class OrientationManager {
    actual fun getOrientation(): Orientation {
        val deviceOrientation = UIDevice.currentDevice.orientation
        return when {
            deviceOrientation == UIDeviceOrientation.UIDeviceOrientationLandscapeLeft ||
                deviceOrientation == UIDeviceOrientation.UIDeviceOrientationLandscapeRight ->
                Orientation.LANDSCAPE
            else -> Orientation.PORTRAIT
        }
    }

    actual fun observeOrientation(onChange: (Orientation) -> Unit): Cancellable {
        val observer = NSNotificationCenter.defaultCenter.addObserverForName(
            UIDeviceOrientationDidChangeNotification,
            null,
            NSOperationQueue.mainQueue,
            { _ -> onChange(getOrientation()) }
        )

        return object : Cancellable {
            override fun cancel() {
                NSNotificationCenter.defaultCenter.removeObserver(observer)
            }
        }
    }
}
