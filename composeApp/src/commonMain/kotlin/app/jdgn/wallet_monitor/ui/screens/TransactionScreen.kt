package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.TopBar
import app.jdgn.wallet_monitor.utils.generateRandomColorHex
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.transaction

@Composable
fun TransactionScreen(navController: NavHostController, id: Long? = null)
{
    val color = remember { mutableStateOf(Color.Gray) }

    LaunchedEffect(Unit) {
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                backButton = true,
                color = color.value,
                title = stringResource(Res.string.transaction)
            )
        }
    ) {

    }
}
