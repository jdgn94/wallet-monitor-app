package app.jdgn.wallet_monitor.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CoinRoutineColorsPalette(
    val profitGreen: Color = Color.Unspecified,
    val lossRed: Color = Color.Unspecified,
)

val profitGreenColor = Color(color = 0xFF32de84)
val lossRedColor = Color(color = 0xFFD2122E)

val darkProfitGreenColor = Color(color = 0xFF32de84)
val darkLossRedColor = Color(color = 0xFFD2122E)

val lightCoinRoutineColorsPalette = CoinRoutineColorsPalette(
    profitGreen = profitGreenColor,
    lossRed = lossRedColor,
)

val darkCoinRoutineColorsPalette = CoinRoutineColorsPalette(
    profitGreen = darkProfitGreenColor,
    lossRed = darkLossRedColor,
)

val localCoinRoutineColorsPalette = compositionLocalOf { CoinRoutineColorsPalette() }
