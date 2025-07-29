package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.NavBar
import app.wallet_monitor.shared.viewModel.AccountViewModel
import app.walletmonitor.db.v0.GetOne
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.account

@Composable
fun AccountScreen(navController: NavHostController, id: Long? = null) {
    val title = stringResource(Res.string.account)
    val viewModel = koinViewModel<AccountViewModel>()
    val account = remember { mutableStateOf<GetOne>(
        GetOne(
            id = 0,
            currencyId = 0,
            bankId = 0,
            amount = 0.0,
            color = "",
            name = "",
            currencyName = "",
            symbol = "",
            decimalDigits = 0,
            code = "",
            rounding = 0,
            symbolNative = "",
            bankName = "",
            image = "",
        )
    ) }

    LaunchedEffect(true) {
        if (id != null) {
            val accountResult = viewModel.getAccount(id)
            if (accountResult != null) {
                account.value = accountResult
            }
        }
    }

    Scaffold(
        topBar = { NavBar(navController = navController, title = title, backButton = true) }
    ) { padding ->
        CustomColumn(modifier = Modifier.padding(padding)) {
            Text(text = account.value.name)
        }
    }
}