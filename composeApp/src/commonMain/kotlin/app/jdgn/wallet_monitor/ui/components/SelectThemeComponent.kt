package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.dark
import walletmonitor.composeapp.generated.resources.light
import walletmonitor.composeapp.generated.resources.system

@Composable
fun SelectThemeComponent() {
    val themes = listOf(
        stringResource(Res.string.system),
        stringResource(Res.string.light),
        stringResource(Res.string.dark)
    )
    val selectedTheme = remember { mutableStateOf(themes[0]) }

    CustomBox(
        margin = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
        widthFraction = 0.5f,
        maxWidthDp = 300.dp
    ) {
        Text(text = selectedTheme.value)
    }
}