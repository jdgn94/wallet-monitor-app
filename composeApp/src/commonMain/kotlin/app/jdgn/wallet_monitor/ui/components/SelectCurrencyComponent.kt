package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.walletmonitor.db.v0.Currencies
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun SelectCurrencyComponent() {
    val viewModel = koinViewModel<CurrencyViewModel>() // view model
    val scope = rememberCoroutineScope() // scope coroutine
    val showDialog = remember { mutableStateOf(false) } // dialog state
    val currencies = remember { mutableStateOf(emptyList<Currencies>()) } // list currencies
    val scrollState = rememberScrollState()

    fun getCurrencies() {
        scope.launch {
            println("obteniendo valores")
            currencies.value = viewModel.getCurrencies()
            println("valores obtenidos: $currencies")
        }
    }

    LaunchedEffect(true) {
        getCurrencies()
    }

    fun test() {
        println("hellow from selectCurrencyComponent")
        showDialog.value = true
    }

    CustomBox(
        margin = PaddingValues(10.dp),
        onClick = { test() }
    ) {
        Text("Hola vale")
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Título del Diálogo") },
            text = {
                FlowRow(
//                    mainAxisSpacing = 8.dp,
//                    crossAxisSpacing = 8.dp,
                    modifier = Modifier
                        .padding(8.dp)
                        .verticalScroll(scrollState)
                ) {
                    currencies.value.forEach { currency ->
                        Text(currency.name)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    // Acción al confirmar
                    showDialog.value = false
                }) {
                    Text("Aceptar")
                }
            }
        )
    }
}
