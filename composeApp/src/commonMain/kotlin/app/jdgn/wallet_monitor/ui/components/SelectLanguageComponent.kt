package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.wallet_monitor.shared.model.LanguageModel
import app.wallet_monitor.shared.viewModel.LanguageViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.selectCurrency
import walletmonitor.composeapp.generated.resources.system

@Composable
fun SelectLanguageComponent() {
    val viewModel = koinViewModel<LanguageViewModel>()
    val languages = viewModel.getLanguages()
    val selectedLanguage = remember{ mutableStateOf(viewModel.getDefaultLanguage()) }
    val openDialog = remember { mutableStateOf(false) }

    fun openCloseDialog() {
        openDialog.value = !openDialog.value
    }

    fun selectLanguage(languageModel: LanguageModel) {
        viewModel.setDefaultLanguage(languageModel)
        selectedLanguage.value = languageModel
        openCloseDialog()
    }

    CustomBox(
        onClick = { openCloseDialog() },
        margin = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
        widthFraction = 0.5f,
        maxWidthDp = 300.dp
    ) {
        Text(text =
            selectedLanguage.value.name.ifEmpty {
                stringResource(Res.string.system)
            }
        )
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
                        if (language == selectedLanguage)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.surface,
                ) {
                    Text(
                        text =
                            language.name.ifEmpty { stringResource(Res.string.system) }
                    )
                }
            }
        }
    )
}
