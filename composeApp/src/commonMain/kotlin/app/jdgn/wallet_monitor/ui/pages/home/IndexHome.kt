package app.jdgn.wallet_monitor.ui.pages.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.AccountsComponent

@Composable
fun IndexHome(navController: NavHostController) {
    AccountsComponent(navController)
}