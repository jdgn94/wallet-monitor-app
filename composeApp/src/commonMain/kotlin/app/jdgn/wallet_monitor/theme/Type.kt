package app.jdgn.wallet_monitor.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import walletmonitor.composeapp.generated.resources.NunitoItalic
import walletmonitor.composeapp.generated.resources.NunitoRegular
import walletmonitor.composeapp.generated.resources.Res



// Default Material 3 typography values
val baseline = Typography()

@Composable
fun appTypography(): Typography {
    val bodyFontFamily = FontFamily(
        Font(Res.font.NunitoRegular,FontWeight.Normal),
        Font(Res.font.NunitoRegular,FontWeight.Bold),
        Font(Res.font.NunitoItalic, FontWeight.Normal),
        Font(Res.font.NunitoItalic,FontWeight.Bold),
    )

    val displayFontFamily = FontFamily(
        Font(Res.font.NunitoRegular,FontWeight.Bold),
        Font(Res.font.NunitoRegular,FontWeight.Normal),
        Font(Res.font.NunitoRegular,FontWeight.Medium),
        Font(Res.font.NunitoRegular,FontWeight.SemiBold),
        Font(Res.font.NunitoItalic, FontWeight.Bold),
        Font(Res.font.NunitoItalic,FontWeight.Normal),
        Font(Res.font.NunitoItalic,FontWeight.Medium),
        Font(Res.font.NunitoItalic,FontWeight.SemiBold),
    )

    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
}
