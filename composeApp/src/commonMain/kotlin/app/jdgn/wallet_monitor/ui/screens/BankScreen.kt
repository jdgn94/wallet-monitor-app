package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.CustomTextField
import app.jdgn.wallet_monitor.ui.components.basic.TopBar
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.bank
import walletmonitor.composeapp.generated.resources.name
import walletmonitor.composeapp.generated.resources.nameNoEmpty

@Composable
fun BankScreen(navController: NavHostController, id: Long? = null) {

    val name = remember { mutableStateOf("") }
    val nameError = remember { mutableStateOf(false) }

    fun changeName(value: String) {
        name.value = value
        if (value.isNotEmpty()) {
            nameError.value = false
            return
        }
        nameError.value = true
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = stringResource(Res.string.bank),
                backButton = true,
            )
        },
    ) { padding ->
        CustomColumn(modifier = Modifier.padding(padding)) {
            CustomTextField(
                value = name.value,
                onChangeValue = { changeName(it) },
                label = stringResource(Res.string.name),
                margin = PaddingValues(horizontal = 16.dp),
                errorText =
                    if (nameError.value)
                        stringResource(Res.string.nameNoEmpty)
                    else
                        null
            )
        }
    }
}
