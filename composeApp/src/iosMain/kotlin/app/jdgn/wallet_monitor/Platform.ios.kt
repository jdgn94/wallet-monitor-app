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
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSTimer
import platform.Foundation.NSRunLoop
import platform.Foundation.NSDefaultRunLoopMode
import platform.Foundation.NSDate
import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

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
actual class ToastManager {
    actual fun showToast(message: String, duration: ToastDuration) {
        val durationSeconds = when (duration) {
            ToastDuration.SHORT -> 2.0
            ToastDuration.LONG -> 3.5
        }

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            ?: return

        val alert = UIAlertController.alertControllerWithTitle(
            title = null,
            message = message,
            preferredStyle = UIAlertControllerStyleAlert
        )

        rootViewController.presentViewController(alert, animated = true, completion = null)

        NSTimer.scheduledTimerWithTimeInterval(
            durationSeconds,
            false,
            { NSTimer ->
                alert.dismissViewControllerAnimated(true, completion = null)
            }
        )
    }
}

actual fun getSystemLanguage(): String {
    val preferred = NSLocale.preferredLanguages.firstOrNull() as? String
    return preferred?.substringBefore("-") ?: "en"
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
