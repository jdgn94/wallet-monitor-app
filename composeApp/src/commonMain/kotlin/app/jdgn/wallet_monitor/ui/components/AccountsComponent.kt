package app.jdgn.wallet_monitor.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.ButtonAddItem
import app.jdgn.wallet_monitor.ui.components.basic.CustomRow
import app.jdgn.wallet_monitor.ui.components.composed.AccountItemComponent
import app.wallet_monitor.shared.viewModel.AccountViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountsComponent(navController: NavHostController) {
    val viewModel = koinViewModel<AccountViewModel>()
    val allAccounts = viewModel.getAccounts()

    fun accountDetails(id: Long) {
        println("go to account details $id")
        navController.navigate("account/$id")
    }

    CustomRow(
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        allAccounts.forEach { account ->
            AccountItemComponent(account, onClick = { accountDetails(it) })
        }
        ButtonAddItem(
            onClick = { navController.navigate("account/") },
        )
    }
}