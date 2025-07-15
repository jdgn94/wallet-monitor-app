package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.jdgn.wallet_monitor.ui.components.composed.CurrencyItemComponent
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.walletmonitor.db.v0.Currencies
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.selectCurrency

@Preview
@Composable
fun SelectCurrencyComponent(defaultCurrencySelectId: Long? = null) {
    val defaultSelectionName = stringResource(Res.string.selectCurrency)
    val viewModel = koinViewModel<CurrencyViewModel>() // view model
    val scope = rememberCoroutineScope() // scope coroutine
    val showDialog = remember { mutableStateOf(false) } // dialog state
    val currencies = remember { mutableStateOf(emptyList<Currencies>()) } // list currencies
    val currencySelect = remember {
        mutableStateOf<Currencies>(Currencies(
            id = 0,
            name = "",
            symbol = "",
            symbolNative = "",
            code = defaultSelectionName,
            decimalDigits = 0,
            rounding = 0,
            currencyTypeId = 0,
            countries = "",
        ))
    }

    fun getCurrencies() {
        scope.launch {
            currencies.value = viewModel.getCurrencies()
        }
    }

    LaunchedEffect(true) {
        getCurrencies()
        if (defaultCurrencySelectId != null) {
             val defaultCurrency = viewModel.getOneById(defaultCurrencySelectId)
            if (defaultCurrency != null) currencySelect.value = defaultCurrency
        }
    }

    fun openCloseDialog() {
        println("hello from selectCurrencyComponent")
        showDialog.value = !showDialog.value
    }

    fun selectCurrency(currency: Currencies) {
        currencySelect.value = currency
        showDialog.value = false
    }

    fun searchCurrency(value: String) {}

    CurrencyItemComponent(
        currency = currencySelect.value,
        margin = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
        widthFraction = 0.5f,
        maxWithDp = 300.dp,
        onSelect = { openCloseDialog() }
    )

    DialogBasic(
        open = showDialog.value,
        onDismissRequest = { openCloseDialog() },
        searchAction = { searchCurrency(it) },
        title = Res.string.selectCurrency,
        body = {
            currencies.value.forEach { currency ->
                CurrencyItemComponent(
                    currency,
                    selected = currency.id == currencySelect.value.id,
                    onSelect = { selectCurrency(it) }
                )
            }
        }
    )
}
