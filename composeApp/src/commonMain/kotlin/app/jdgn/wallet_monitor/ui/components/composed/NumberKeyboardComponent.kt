package app.jdgn.wallet_monitor.ui.components.composed
// Archivo: NumberKeyboardComponent.kt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.theme.extraColor
import app.jdgn.wallet_monitor.ui.LocalResource.Icons
import app.jdgn.wallet_monitor.ui.components.basic.AdaptiveText
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.wallet_monitor.shared.viewModel.NumberKeyboardViewModel
import app.walletmonitor.db.v0.Currencies
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.amount

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NumberKeyboardComponent(
    margin: PaddingValues = PaddingValues(),
    color: Color = MaterialTheme.colorScheme.primary,
    amount: Double = 0.0,
    currency: Currencies = Currencies(
        id = 0L,
        name = "USD",
        symbol = "$",
        symbolNative = "$",
        code = "usd",
        decimalDigits = 2,
        rounding = 2,
        currencyTypeId = 1,
        countries = ""
    ),
    updateAmount: (Double) -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val viewModel = koinInject<NumberKeyboardViewModel>()

    LaunchedEffect(amount) {
        viewModel.initialize(amount, currency)
    }

    val keyboardNumber = listOf( "C", "±", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "00", "0", "backspace", "=")

    CustomBox(
        margin = margin,
        padding = PaddingValues(16.dp),
        widthFraction = 1f,
        maxWidthDp = 10000.dp,
        color = color,
        contentAlignment = Alignment.TopStart,
        onClick = { viewModel.openSheet() }
    ) {
        Column() {
            Text(
                text = stringResource(Res.string.amount),
                style = MaterialTheme.typography.bodySmall
            )
            AdaptiveText(
                text = "${currency.symbolNative} ${viewModel.amountFormatted.value}",
                color = color
            )
        }
    }

    if (viewModel.isSheetVisible.value) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.closeSheet() },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            // Sheet content
            CustomColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AdaptiveText(
                    text = "${currency.symbolNative} ${viewModel.amountTempFormatted.value}",
                    color = MaterialTheme.colorScheme.primary,
                )
                FlowRow(
                    modifier = Modifier.width(280.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.Center,
                ) {
                    keyboardNumber.map { value ->
                        val internalColor = when (value) {
                            "C", "backspace" -> MaterialTheme.colorScheme.error
                            "±","%", "÷", "×", "-", "+", "." -> MaterialTheme.colorScheme.extraColor.info.color
                            "=" -> MaterialTheme.colorScheme.extraColor.success.color
                            else -> MaterialTheme.colorScheme.primary
                        }
                        CustomBox(
                            pickWidth = 60.dp,
                            margin = PaddingValues(vertical = 6.dp),
                            color = internalColor,
                            onClick = { viewModel.handleKeyboardInput(value, updateAmount) }
                        ) {
                            when (value) {
                                "backspace" -> Icon(
                                    painter = painterResource(Icons.delete_keyboard),
                                    contentDescription = "delete_keyboard",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "+" -> Icon(
                                    painter = painterResource(Icons.plus),
                                    contentDescription = "plus",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "-" -> Icon(
                                    painter = painterResource(Icons.minus),
                                    contentDescription = "minus",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "×" -> Icon(
                                    painter = painterResource(Icons.clear),
                                    contentDescription = "clear",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "÷" -> Icon(
                                    painter = painterResource(Icons.divide),
                                    contentDescription = "divide",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "±" -> Icon(
                                    painter = painterResource(Icons.plus_minus),
                                    contentDescription = "plus_minus",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "%" -> Icon(
                                    painter = painterResource(Icons.percentage),
                                    contentDescription = "percentage",
                                    tint = internalColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                "=" -> {
                                    if (viewModel.isPendingCalculating.value)
                                        Icon(
                                            painter = painterResource(Icons.equal),
                                            contentDescription = "equal",
                                            tint = internalColor,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    else
                                        Icon(
                                            painter = painterResource(Icons.check),
                                            contentDescription = "check",
                                            tint = internalColor,
                                            modifier = Modifier.size(24.dp)
                                        )
                                }
                                else -> Text(
                                    text = value,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = internalColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
