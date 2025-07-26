package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.pages.config.SetDefaultCurrency
import app.jdgn.wallet_monitor.ui.pages.config.SetDefaultLanguage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun InitialConfigScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(
            modifier = Modifier.verticalScroll(scrollState).padding(it)
        ) {
            SetDefaultCurrency()
            SetDefaultLanguage()
        }
    }
}
