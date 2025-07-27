package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.wallet_monitor.shared.APP_THEME_KEY
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.viewModel.UserPreferenceViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.dark
import walletmonitor.composeapp.generated.resources.light
import walletmonitor.composeapp.generated.resources.selectTheme
import walletmonitor.composeapp.generated.resources.system

@Composable
fun SelectThemeComponent() {
    val themes = listOf(
        stringResource(Res.string.system),
        stringResource(Res.string.light),
        stringResource(Res.string.dark)
    )
    val selectedTheme = remember { mutableStateOf(themes[0]) }
    val showDialog = remember { mutableStateOf(false) }
    val viewModel = koinViewModel<UserPreferenceViewModel>()

    fun openCloseDialog() {
        showDialog.value = !showDialog.value
    }

    fun selectTheme(value: String) {
        selectedTheme.value = value
        val theme = when (value) {
            themes[0] -> {
                "system"
            }
            themes[1] -> {
                "light"
            }
            else -> {
                "dark"
            }
        }
        viewModel.setString(APP_THEME_KEY, theme)
        GlobalStateManager.changeTheme()
        openCloseDialog()
    }

    CustomBox(
        onClick = { openCloseDialog() },
        margin = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
        widthFraction = 0.5f,
        maxWidthDp = 300.dp
    ) {
        Text(text = selectedTheme.value)
    }


    DialogBasic(
        open = showDialog.value,
        onDismissRequest = { openCloseDialog() },
        title = Res.string.selectTheme,
        showActions = true,
        cancelText = Res.string.close,
        content = {
            themes.forEach { theme ->
                CustomBox(
                    margin = PaddingValues(vertical = 5.dp),
                    onClick = { selectTheme(theme) },
                    backgroundColor =
                        if (theme == selectedTheme.value)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface,
                ) {
                    Text(text = theme)
                }
            }
        }
    )
}