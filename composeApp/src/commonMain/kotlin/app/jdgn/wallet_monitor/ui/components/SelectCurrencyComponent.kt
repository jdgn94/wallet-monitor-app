package app.jdgn.wallet_monitor.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.jdgn.wallet_monitor.ui.components.composed.CurrencyItemComponent
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.walletmonitor.db.v0.Currencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.currencyDeprecated
import walletmonitor.composeapp.generated.resources.selectCurrency

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun SelectCurrencyComponent(defaultCurrencySelectId: Long? = null) {
    val defaultSelectionName = stringResource(Res.string.selectCurrency)
    val viewModel = koinViewModel<CurrencyViewModel>() // view model
    val scope = rememberCoroutineScope() // scope coroutine
    val showDialog = remember { mutableStateOf(false) } // dialog state
    val currencies = remember { mutableStateOf(emptyList<Currencies>()) } // list currencies
    var visibleCharCount by remember { mutableStateOf(0) } // state to animate deprecate currency
    val deprecatedText = stringResource(Res.string.currencyDeprecated)
    val currencySelect = remember {
        mutableStateOf(Currencies(
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

    fun getCurrencies(name: String = "") {
        scope.launch {
            currencies.value = viewModel.getAllByNameAndType(name, 1)
        }
    }

    LaunchedEffect(true) {
        if (defaultCurrencySelectId != null) {
             val defaultCurrency = viewModel.getOneById(defaultCurrencySelectId)
            if (defaultCurrency != null) currencySelect.value = defaultCurrency
        }
    }

    // Effect to animate text
    LaunchedEffect(currencySelect.value) {
        // Reset animation on change currency
        visibleCharCount = 0

        // if currency is deprecated, start de animation
        if (currencySelect.value.currencyTypeId == 1.toLong() && currencySelect.value.countries == "[]") {
            // animate al character one by one
            for (i in 1..deprecatedText.length) {
                visibleCharCount = i
                delay(50)
            }
        }
    }

    fun openCloseDialog() {
        println("hello from selectCurrencyComponent")
        getCurrencies()
        showDialog.value = !showDialog.value
    }

    fun selectCurrency(currency: Currencies) {
        currencySelect.value = currency
        showDialog.value = false
    }

    fun searchCurrency(value: String) {
        println("search currency by $value")
        getCurrencies(value)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CurrencyItemComponent(
            currency = currencySelect.value,
            margin = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
            widthFraction = 0.5f,
            maxWidthDp = 300.dp,
            onSelect = { openCloseDialog() }
        )
        // Text animated for deprecated currency
        if (currencySelect.value.currencyTypeId == 1.toLong() && currencySelect.value.countries == "[]") {
            AnimatedContent(
                targetState = deprecatedText.take(visibleCharCount),
                transitionSpec = {
                    fadeIn(animationSpec = tween(150)) togetherWith
                            fadeOut(animationSpec = tween(150))
                }
            ) { visibleText ->
                Text(
                    text = visibleText,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    DialogBasic(
        open = showDialog.value,
        onDismissRequest = { openCloseDialog() },
        searchAction = { searchCurrency(it) },
        title = Res.string.selectCurrency,
        showActions = true,
        cancelText = Res.string.close,
        content = {
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
