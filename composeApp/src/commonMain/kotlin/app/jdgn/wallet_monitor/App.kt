package app.jdgn.wallet_monitor

import androidx.compose.runtime.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.jdgn.wallet_monitor.theme.AppTheme
import app.jdgn.wallet_monitor.ui.src.Navigation
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.UserPreferences
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>
) = KoinContext {
    val userPreferences = UserPreferences(prefs)
    GlobalStateManager.userPreferences = userPreferences


    AppTheme(
        darkTheme = userPreferences.getBoolean("dark_mode").collectAsState(false).value,
//        dynamicColor = userPreferences.getBoolean("dynamic_color").collectAsState(false).value,
    ) {
//        testingPage()
        Navigation()
    }
}
