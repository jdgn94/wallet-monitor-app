package app.jdgn.wallet_monitor.utils

import androidx.compose.ui.graphics.Color

// convert hex string to color #FF5C6BC0 (ARGB)
fun hexStringToColor(hexString: String): Color {
    val cleanHexString = hexString.removePrefix("#")
    val colorLong = cleanHexString.toLong(16)
    return Color(colorLong)
}
