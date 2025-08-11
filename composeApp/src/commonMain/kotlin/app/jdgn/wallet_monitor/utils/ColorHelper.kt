package app.jdgn.wallet_monitor.utils

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color

// convert hex string to color #FF5C6BC0 (ARGB)
fun hexStringToColor(hexString: String): Color {
    val cleanHexString = hexString.removePrefix("#")
    val colorLong = cleanHexString.toLong(16)
    return Color(colorLong)
}

fun colorToHex(color: Color): String {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    val alpha = (color.alpha * 255).toInt()

    return "#${alpha.toString(16).padStart(2, '0')}${red.toString(16).padStart(2, '0')}${green.toString(16).padStart(2, '0')}${blue.toString(16).padStart(2, '0')}".uppercase()
}
