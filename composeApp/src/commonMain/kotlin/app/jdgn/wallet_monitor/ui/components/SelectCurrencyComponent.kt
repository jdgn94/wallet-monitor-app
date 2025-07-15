package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.jdgn.wallet_monitor.getScreenHeight
import app.jdgn.wallet_monitor.getScreenWidth
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
fun SelectCurrencyComponent() {
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
    }

    fun openCloseDialog() {
        println("hello from selectCurrencyComponent")
        showDialog.value = !showDialog.value
    }

    fun selectCurrency(currency: Currencies) {
        currencySelect.value = currency
        showDialog.value = false
    }

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
