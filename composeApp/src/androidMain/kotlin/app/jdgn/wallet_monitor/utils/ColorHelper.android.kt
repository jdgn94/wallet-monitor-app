package app.jdgn.wallet_monitor.utils

actual fun formatHex(colorInt: Int): String {
    return String.format("#%06x", colorInt)
}
