package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.NavBar
import app.jdgn.wallet_monitor.ui.pages.home.IndexHome

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
            IndexHome(navController)
        }

    }
}