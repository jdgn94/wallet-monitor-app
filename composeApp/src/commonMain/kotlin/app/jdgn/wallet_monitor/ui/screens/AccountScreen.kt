package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.SelectBankComponent
import app.jdgn.wallet_monitor.ui.components.SelectCurrencyComponent
import app.jdgn.wallet_monitor.ui.components.basic.ColorPicker
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.CustomTextField
import app.jdgn.wallet_monitor.ui.components.basic.NavBar
import app.jdgn.wallet_monitor.utils.colorToHex
import app.jdgn.wallet_monitor.utils.hexStringToColor
import app.wallet_monitor.shared.viewModel.AccountViewModel
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.walletmonitor.db.v0.Banks
import app.walletmonitor.db.v0.Currencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.account
import walletmonitor.composeapp.generated.resources.bank
import walletmonitor.composeapp.generated.resources.name
import walletmonitor.composeapp.generated.resources.nameNoEmpty
import walletmonitor.composeapp.generated.resources.none

@Composable
fun AccountScreen(navController: NavHostController, id: Long? = null) {
    val viewModelAccount = koinViewModel<AccountViewModel>()
    val viewModelCurrency = koinViewModel<CurrencyViewModel>()
    val accountId = rememberSaveable { mutableStateOf(id ?: 0L) }
    val colorString = rememberSaveable { mutableStateOf("#FF5C6BC0") }
    val amount = rememberSaveable { mutableStateOf(0.0) }
    val bankId = rememberSaveable { mutableStateOf(0L) }
    val color = remember { mutableStateOf<Color>(hexStringToColor(colorString.value)) }
    val name = rememberSaveable { mutableStateOf("") }
    val currency = remember { mutableStateOf(viewModelCurrency.getDefaultCurrency()!!) }
    val currencyId = rememberSaveable { mutableStateOf(currency.value.id) }
    val nameError = rememberSaveable { mutableStateOf(false) }

    fun onChangeCurrency(value: Currencies) {
        currency.value = value
        currencyId.value = value.id
    }

    fun onChangeColor(value: String) {
        colorString.value = value
        color.value = hexStringToColor(value)
    }

    LaunchedEffect(true) {
        if (id != null && name.value.isEmpty()) {
            val accountResult = viewModelAccount.getAccount(id)
            if (accountResult != null) {
                launch {
                    name.value = accountResult.name
                    amount.value = accountResult.amount
                    bankId.value = accountResult.bankId ?: 0L
                    delay(10)
                    onChangeColor(accountResult.color)
                    onChangeCurrency(viewModelCurrency.getOneById(accountResult.currencyId)!!)
                }
            }
        } else if (id != null && name.value.isNotEmpty()) {
            onChangeColor(colorString.value)
            onChangeCurrency(viewModelCurrency.getOneById(currencyId.value)!!)
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

    fun changeBank(bank: Banks) {}

    Scaffold(
        topBar = {
            NavBar(
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
                changeColor = { onChangeColor(colorToHex(it)) }
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
                    minusWidthFraction = 24.dp,
                    widthFraction = 0.5f,
                    changeCurrency = { onChangeCurrency(it) }
                )
                SelectBankComponent(
                    color = color.value,
                    margin = PaddingValues(0.dp),
                    padding = PaddingValues(16.dp),
                    minusWidthFraction = 24.dp,
                    widthFraction = 0.5f,
                    onSelectBank = { changeBank(it) }
                )
            }
        }
    }
}