package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.ButtonAddItem
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.jdgn.wallet_monitor.ui.components.composed.BankItemComponent
import app.wallet_monitor.shared.viewModel.BankViewModel
import app.walletmonitor.db.v0.Banks
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.selectBank

@Composable
fun SelectBankComponent(
    modifier: Modifier = Modifier,
    pickWidth: Dp? = null,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(),
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color? = null,
    onClick: (() -> Unit)? = null,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp,
    contentAlignment: Alignment = Alignment.CenterStart,
    minusWidthFraction: Dp = 0.dp,
    defaultBankSelectId: Long? = null,
    changeDefaultBank: Boolean = false,
    onSelectBank: ((bank: Banks) -> Unit)
) {
    val viewModel = koinViewModel<BankViewModel>() // view model
    val banks = remember { mutableStateOf(viewModel.getBanks()) } // list banks
    val bankSelect = remember { mutableStateOf(banks.value.find { it.id == defaultBankSelectId }) } // selected bank
    val showDialog = remember { mutableStateOf(false) } // dialog state

    LaunchedEffect(defaultBankSelectId) {
        bankSelect.value = banks.value.find { it.id == defaultBankSelectId }
    }

    fun openCloseDialog() {
        showDialog.value = !showDialog.value
    }

    fun selectBank(bank: Banks) {
        bankSelect.value = bank
        openCloseDialog()
        onSelectBank(bank)
    }

    fun reloadBanks() {
        banks.value = viewModel.getReloadBanks();
    }

    DialogBasic(
        open = showDialog.value,
        onDismissRequest = { openCloseDialog() },
        title = Res.string.selectBank,
        showActions = true,
        cancelText = Res.string.close,
    ) {
        banks.value.forEach { bank ->
            BankItemComponent(
                modifier = modifier,
                bank = bank,
                padding = padding,
                margin = PaddingValues(3.dp),
                widthFraction = 0.3333334f,
                backgroundColor = backgroundColor,
                onClick = { selectBank(bank) }
            )
        }
        CreateEditBankComponent(reloadComponent = { reloadBanks() })
    }

    BankItemComponent(
        modifier = modifier,
        bank = bankSelect.value,
        pickWidth = pickWidth,
        padding = padding,
        margin = margin,
        color = color,
        backgroundColor = backgroundColor,
        widthFraction = widthFraction,
        maxWidthDp = maxWidthDp,
        contentAlignment = contentAlignment,
        minusWidthFraction = minusWidthFraction,
        onClick = { openCloseDialog() },
    )
}
