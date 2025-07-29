package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.NavBar
import app.jdgn.wallet_monitor.ui.pages.home.AccountsHome
import app.wallet_monitor.shared.viewModel.AccountViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            NavBar(
                navController = navController,
                title = "Home",
                backButton = false
            )
        }
    ) { padding ->
        CustomColumn(modifier = Modifier.padding(padding)) {
            AccountsHome(navController)
            AccountsHome(navController)
            AccountsHome(navController)
            AccountsHome(navController)
            AccountsHome(navController)
            AccountsHome(navController)
            AccountsHome(navController)
            AccountsHome(navController)
            Text("Otro contenido")
        }

    }
}