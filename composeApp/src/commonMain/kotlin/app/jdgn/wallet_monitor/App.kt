package app.jdgn.wallet_monitor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import app.jdgn.wallet_monitor.theme.AppTheme
import app.jdgn.wallet_monitor.ui.src.Navigation
import app.wallet_monitor.shared.APP_THEME_KEY
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.viewModel.UserPreferenceViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    onLanguageChanged: () -> Unit = {},
) {
    val viewModel = koinViewModel<UserPreferenceViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    val darkTheme = remember { mutableStateOf(viewModel.getString(APP_THEME_KEY)) }

    fun onChangeTheme() {
        darkTheme.value = viewModel.getString(APP_THEME_KEY)
    }

    GlobalStateManager.languageChange = onLanguageChanged
    GlobalStateManager.changeTheme = { onChangeTheme() }


    @Composable
    fun getTheme(): Boolean {
        if (darkTheme.value == "dark") return true
        if (darkTheme.value == "light") return false
        return isSystemInDarkTheme()
    }

    AppTheme(
        darkTheme = getTheme(),
//        dynamicColor = userPreferences.getBoolean("dynamic_color").collectAsState(false).value,
    ) {
        Box(
            modifier = Modifier.imePadding().clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { keyboardController?.hide() }
            )
        ) {
            Navigation()
        }
    }
}
