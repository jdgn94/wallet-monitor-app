package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.pages.config.SetDefaultCurrency
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun InitialConfigScreen(navController: NavHostController) {
    Scaffold {
        Box(Modifier.padding(it)) {
            SetDefaultCurrency()
        }
    }
}