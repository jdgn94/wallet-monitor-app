package app.jdgn.wallet_monitor.ui.src

import androidx.navigation.NavBackStackEntry

actual fun NavBackStackEntry.getArgumentString(key: String): String? {
    return arguments?.getString(key)
}