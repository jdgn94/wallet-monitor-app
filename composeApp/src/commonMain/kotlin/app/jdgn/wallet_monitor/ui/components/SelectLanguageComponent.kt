package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.selectCurrency
import walletmonitor.composeapp.generated.resources.system

@Composable
fun SelectLanguageComponent() {
    val languages = listOf(stringResource(Res.string.system), "English", "Español")
    val selectedLanguage = remember { mutableStateOf(languages[0]) }
    val openDialog = remember { mutableStateOf(false) }

    fun openCloseDialog() {
        openDialog.value = !openDialog.value
    }

    fun selectLanguage(language: String) {
        selectedLanguage.value = language
        openCloseDialog()
    }

    CustomBox(
        onClick = { openCloseDialog() },
    ) {
        Text(text = selectedLanguage.value)
    }

    DialogBasic(
        open = openDialog.value,
        onDismissRequest = { openCloseDialog() },
        title = Res.string.selectCurrency,
        showActions = true,
        cancelText = Res.string.close,
        content = {
            languages.forEach { language ->
                CustomBox(
                    margin = PaddingValues(vertical = 5.dp),
                    onClick = { selectLanguage(language) },
                    backgroundColor =
                        if (language == selectedLanguage.value)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface,
                ) {
                    Text(
                        text = language,
                    )
                }
            }
        }
    )
}
