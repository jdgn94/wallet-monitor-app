package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.jdgn.wallet_monitor.ui.components.basic.ButtonAddItem
import app.jdgn.wallet_monitor.ui.components.basic.CustomRow
import app.jdgn.wallet_monitor.ui.components.composed.AccountItemComponent
import app.wallet_monitor.shared.viewModel.AccountViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountsComponent(navController: NavHostController) {
    val viewModel = koinViewModel<AccountViewModel>()
    val allAccounts = remember { mutableStateOf(viewModel.getAccounts()) }

    // Listen for navigation changes and refresh data
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        // Refresh accounts when returning to this screen
        allAccounts.value = viewModel.getReloadAccounts()
    }

    fun accountDetails(id: Long) {
        println("go to account details $id")
        navController.navigate("account/$id")
    }

    CustomRow(
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        allAccounts.value.forEach { account ->
            AccountItemComponent(account, onClick = { accountDetails(it) })
        }
        ButtonAddItem(
            pickWidth = 150.dp,
            padding = PaddingValues(12.dp),
            onClick = { navController.navigate("account/") },
        ) {
            Text(
                text = "",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}