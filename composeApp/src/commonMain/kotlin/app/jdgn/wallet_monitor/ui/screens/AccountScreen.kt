package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.theme.extraColor
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Outlined
import app.jdgn.wallet_monitor.ui.components.SelectBankComponent
import app.jdgn.wallet_monitor.ui.components.SelectCurrencyComponent
import app.jdgn.wallet_monitor.ui.components.basic.ColorPicker
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.CustomTextField
import app.jdgn.wallet_monitor.ui.components.basic.TopBar
import app.jdgn.wallet_monitor.ui.components.composed.ActionButtonComponent
import app.jdgn.wallet_monitor.ui.components.composed.NumberKeyboardComponent
import app.jdgn.wallet_monitor.utils.colorToHex
import app.jdgn.wallet_monitor.utils.hexStringToColor
import app.wallet_monitor.shared.viewModel.AccountViewModel
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.walletmonitor.db.v0.Banks
import app.walletmonitor.db.v0.Currencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.account
import walletmonitor.composeapp.generated.resources.cancel
import walletmonitor.composeapp.generated.resources.delete
import walletmonitor.composeapp.generated.resources.name
import walletmonitor.composeapp.generated.resources.nameNoEmpty
import walletmonitor.composeapp.generated.resources.save

@Composable
fun AccountScreen(navController: NavHostController, id: Long? = null) {
    val viewModelAccount = koinViewModel<AccountViewModel>()
    val viewModelCurrency = koinViewModel<CurrencyViewModel>()
    val accountId = remember { mutableStateOf(id ?: 0L) }
    val amount = remember { mutableStateOf(0.0) }
    val bankId = remember { mutableStateOf(0L) }
    val color = remember { mutableStateOf<Color>(hexStringToColor("#FF5C6BC0")) }
    val name = remember { mutableStateOf("") }
    val currency = remember { mutableStateOf(viewModelCurrency.getDefaultCurrency()!!) }
    val nameError = remember { mutableStateOf(false) }

    fun onChangeCurrency(value: Currencies) {
        currency.value = value
    }

    fun onChangeColor(value: Color) {

        color.value = value
    }

    LaunchedEffect(true) {
        if (id != null) {
            val accountResult = viewModelAccount.getAccount(id)
            if (accountResult != null) {
                println("color guardado: ${accountResult.color}")
                launch {
                    name.value = accountResult.name
                    amount.value = accountResult.amount
                    bankId.value = accountResult.bankId ?: 0L
                    delay(10)
                    onChangeColor(hexStringToColor(accountResult.color))
                    onChangeCurrency(viewModelCurrency.getOneById(accountResult.currencyId)!!)
                }
            }
        }
    }

    fun changeName(value: String) {
        name.value = value
        if (value.isNotEmpty()) {
            nameError.value = false
            return
        }
        nameError.value = true
    }

    fun changeBank(bank: Banks) {
        bankId.value = bank.id
    }

    fun saveAccount() {
        if (name.value.isEmpty()) {
            nameError.value = true
            return
        }
        println("guardando el color: ${colorToHex(color.value)}")
        if (id != null) {
            viewModelAccount.updateAccount(
                id = accountId.value,
                currencyId = currency.value.id,
                bankId = bankId.value,
                amount = amount.value,
                color =  colorToHex(color.value),
                name = name.value
            )
        } else {
            viewModelAccount.createAccount(
                currencyId = currency.value.id,
                bankId = bankId.value,
                amount = amount.value,
                color =  colorToHex(color.value),
                name = name.value
            )
        }
        navController.previousBackStackEntry?.savedStateHandle?.set("account_saved", true)
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = stringResource(Res.string.account),
                backButton = true,
                color = color.value
            )
        }
    ) { padding ->
        CustomColumn(modifier = Modifier.padding(padding)) {
            CustomTextField(
                value = name.value,
                onChangeValue = { changeName(it) },
                label = stringResource(Res.string.name),
                margin = PaddingValues(horizontal = 16.dp),
                color = color.value,
                errorText =
                    if (nameError.value)
                        stringResource(Res.string.nameNoEmpty)
                    else
                        null
            )
            ColorPicker(
                margin = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                initialColor = color.value,
                maxWidthDp = 10000.dp,
                widthFraction = 1f,
                changeColor = { onChangeColor(it) }
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Companion.CenterVertically
            ) {
                SelectCurrencyComponent(
                    defaultCurrencySelectId = currency.value.id,
                    color = color.value,
                    margin = PaddingValues(0.dp),
                    minusWidthFraction = 20.dp,
                    widthFraction = 0.5f,
                    changeCurrency = { onChangeCurrency(it) },
                    height = 80.dp
                )
                SelectBankComponent(
                    color = color.value,
                    margin = PaddingValues(0.dp),
                    padding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                    minusWidthFraction = 20.dp,
                    bankSelectId = bankId.value,
                    widthFraction = 0.5f,
                    onSelectBank = { changeBank(it) },
                    height = 80.dp
                )
            }
            NumberKeyboardComponent(
                margin = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                color = color.value,
                currency = currency.value,
                amount = amount.value,
                updateAmount = { amount.value = it }
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = 16.dp ).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.Center
            ) {
                ActionButtonComponent(
                    backgroundColor = MaterialTheme.colorScheme.extraColor.success.color,
                    color = MaterialTheme.colorScheme.extraColor.success.onColor,
                    shadowColor = color.value,
                    icon = painterResource(Outlined.save),
                    iconDescription = "Save",
                    text = stringResource(Res.string.save),
                    onClick = { saveAccount() }
                )
                ActionButtonComponent(
                    color = MaterialTheme.colorScheme.error,
                    icon = painterResource(Outlined.cancel),
                    shadowColor = color.value,
                    iconDescription = "Cancel",
                    text = stringResource(Res.string.cancel),
                    onClick = { navController.popBackStack() }
                )
                ActionButtonComponent(
                    backgroundColor = MaterialTheme.colorScheme.error,
                    color = MaterialTheme.colorScheme.onError,
                    shadowColor = color.value,
                    icon = painterResource(Outlined.delete),
                    iconDescription = "Delete",
                    text = stringResource(Res.string.delete),
                    onClick = {}
                )
            }
        }
    }
}
