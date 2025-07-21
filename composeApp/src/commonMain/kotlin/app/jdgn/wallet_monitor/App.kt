package app.jdgn.wallet_monitor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.jdgn.wallet_monitor.theme.AppTheme
import app.jdgn.wallet_monitor.ui.src.Navigation
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.UserPreferences
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    val userPreferences = UserPreferences(prefs)
    GlobalStateManager.userPreferences = userPreferences


    AppTheme(
        darkTheme = userPreferences.getBoolean("dark_mode").collectAsState(false).value,
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
