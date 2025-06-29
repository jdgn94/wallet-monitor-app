package app.jdgn.wallet_monitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.jdgn.wallet_monitor.ui.theme.AppTheme
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.UserPreferences
import app.wallet_monitor.shared.viewModel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>
) {
    KoinContext {
        val userPreferences = UserPreferences(prefs)
        GlobalStateManager.userPreferences = userPreferences
        AppTheme(
            darkTheme = userPreferences.getBoolean("dark_mode").collectAsState(false).value,
            dynamicColor = userPreferences.getBoolean("dynamic_color").collectAsState(false).value,
        ) {
            testingPage()
        }
    }
}