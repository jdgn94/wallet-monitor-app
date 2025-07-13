package app.jdgn.wallet_monitor.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.RobotoFlex
import org.jetbrains.compose.resources.Font

@Composable
fun AppTypography(): Typography {
    val bonaNovaSC = FontFamily(
        Font(Res.font.RobotoFlex, FontWeight.Bold),
        Font(Res.font.RobotoFlex, FontWeight.Normal),
    )

    val dynaPuff = FontFamily(
        Font(Res.font.RobotoFlex, FontWeight.Bold),
        Font(Res.font.RobotoFlex, FontWeight.Normal),
        Font(Res.font.RobotoFlex, FontWeight.Medium),
        Font(Res.font.RobotoFlex, FontWeight.SemiBold),
    )

    return Typography(
        headlineLarge = TextStyle(
            fontFamily = dynaPuff,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = bonaNovaSC,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        displaySmall = TextStyle(
            fontFamily = dynaPuff,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    )
}
