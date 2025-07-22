package app.jdgn.wallet_monitor

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import android.content.Context
import android.content.res.Configuration
import android.hardware.display.DisplayManager
import android.widget.Toast
import java.util.Locale

actual val currentPlatform = Platform.ANDROID

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
actual fun getScreenWidth(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
actual fun getScreenHeight(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp.dp
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ToastManager(private val context: Context) {
    actual fun showToast(message: String, duration: ToastDuration) {
        val toastDuration = when (duration) {
            ToastDuration.SHORT -> Toast.LENGTH_SHORT
            ToastDuration.LONG -> Toast.LENGTH_LONG
        }

        Toast.makeText(context, message, toastDuration).show()
    }
}

actual fun getSystemLanguage(): String {
    return Locale.getDefault().language
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class OrientationManager(private val context: Context) {
    actual fun getOrientation(): Orientation {
        val orientation = context.resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Orientation.LANDSCAPE
        } else {
            Orientation.PORTRAIT
        }
    }

    actual fun observeOrientation(onChange: (Orientation) -> Unit): Cancellable {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val listener = object : DisplayManager.DisplayListener {
            override fun onDisplayAdded(displayId: Int) {}
            override fun onDisplayRemoved(displayId: Int) {}
            override fun onDisplayChanged(displayId: Int) {
                onChange(getOrientation())
            }
        }

        displayManager.registerDisplayListener(listener, null)

        return object : Cancellable {
            override fun cancel() {
                displayManager.unregisterDisplayListener(listener)
            }
        }
    }
}
