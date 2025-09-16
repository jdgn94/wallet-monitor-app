package app.jdgn.wallet_monitor.utils

actual fun formatHex(colorInt: Int): String {
    val hexString = colorInt.toString(16)
    return "#" + hexString.padStart(6, '0')
}
