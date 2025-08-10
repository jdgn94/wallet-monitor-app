package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.ButtonAddItem
import app.jdgn.wallet_monitor.ui.components.basic.CustomTextField
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.wallet_monitor.shared.viewModel.BankViewModel
import app.walletmonitor.db.v0.Banks
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.bank
import walletmonitor.composeapp.generated.resources.cancel
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.create
import walletmonitor.composeapp.generated.resources.createBank
import walletmonitor.composeapp.generated.resources.name
import walletmonitor.composeapp.generated.resources.nameNoEmpty
import walletmonitor.composeapp.generated.resources.none
import walletmonitor.composeapp.generated.resources.save
import walletmonitor.composeapp.generated.resources.updateBank

@Composable
fun CreateEditBankComponent(
    bank: Banks? = null,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(3.dp),
    widthFraction: Float = 0.5f,
    reloadComponent: (() -> Unit)? = null
) {
    val viewModel = koinViewModel<BankViewModel>() // view model
    val name = remember { mutableStateOf("") }
    val nameError = remember { mutableStateOf(false) }
    val image = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) } // dialog state

    LaunchedEffect(bank) {
        if (bank != null) {
            name.value = bank.name
            image.value = bank.image ?: ""
        } else {
            name.value = ""
            image.value = ""
        }
    }

    fun changeName(value: String) {
        name.value = value
        nameError.value = name.value.isEmpty()
    }

    fun openCloseDialog() {
        showDialog.value = !showDialog.value
    }

    fun confirmAction() {
        if (name.value.isEmpty()) {
            nameError.value = true
            return
        }
        if (bank == null) {
            viewModel.createBank( name = name.value, image = image.value)
        } else {
            viewModel.updateBank(id = bank.id,name = name.value, image = image.value)
        }
        openCloseDialog()
        reloadComponent?.invoke()
    }

    DialogBasic(
        open = showDialog.value,
        onDismissRequest = { openCloseDialog() },
        title = if (bank == null) Res.string.createBank else Res.string.updateBank,
        showActions = true,
        cancelText = if (bank == null) Res.string.close else Res.string.cancel,
        confirmText = if (bank == null) Res.string.create else Res.string.save,
        onConfirmRequest = { confirmAction() },
    ) {
        CustomTextField(
            value = name.value,
            errorText = if (nameError.value) stringResource(Res.string.nameNoEmpty) else "",
            onChangeValue = { changeName(it) },
            label = stringResource(Res.string.name)
        )
    }

    ButtonAddItem(
        padding = padding,
        margin = margin,
        widthFraction = widthFraction,
        onClick = { openCloseDialog() },
    ) {
        Text(
            text = "",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
