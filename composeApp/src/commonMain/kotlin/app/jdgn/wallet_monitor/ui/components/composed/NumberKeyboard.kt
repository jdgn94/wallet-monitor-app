package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.layout.Arrangement
import kotlin.math.pow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.jdgn.wallet_monitor.theme.extraColor
import app.jdgn.wallet_monitor.ui.LocalResource.Icons
import app.jdgn.wallet_monitor.ui.components.basic.AdaptiveText
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.utils.convertStringToDecimal
import app.jdgn.wallet_monitor.utils.formatNumber
import app.walletmonitor.db.v0.Currencies
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.amount
import walletmonitor.composeapp.generated.resources.confirm

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NumberKeyboard(
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
    updateAmount: (Double) -> Unit = {}
) {
    val numberFormat = "comma_dot"
    val currentOperator = remember { mutableStateOf("") }
    val previousNumber = remember { mutableStateOf("") }
    val isCalculating = remember { mutableStateOf(false) }
    val showSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val amountCurrent = remember { mutableStateOf(amount) }
    val amountTemp = remember { mutableStateOf("") }
    val amountTempFormatted = remember { mutableStateOf("") }
    val keyboardNumber = listOf( "C", "±", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "00", "0", "backspace", "=")

    val amountFormatted = remember {
        mutableStateOf(
            formatNumber(
                number = amountCurrent.value,
                formatType = numberFormat,
                decimalDigits = currency.decimalDigits.toInt()
            )
        )
    }

    LaunchedEffect(amount) {
        amountCurrent.value = amount
        amountFormatted.value = formatNumber(
            number = amountCurrent.value,
            formatType = numberFormat,
            decimalDigits = 2
        )
    }

    LaunchedEffect(amountTemp.value) {
        if (amountTemp.value.isNotEmpty()) {
            // Handle decimal display formatting
            val displayValue = if (amountTemp.value.contains(".")) {
                // For decimal input, show as-is with proper formatting
                val parts = amountTemp.value.split(".")
                val integerPart = parts[0].ifEmpty { "0" }
                val decimalPart = if (parts.size > 1) parts[1] else ""

                val integerFormatted = formatNumber(
                    number = integerPart.toDoubleOrNull() ?: 0.0,
                    formatType = numberFormat,
                    decimalDigits = 0
                ).replace(".00", "").replace(",00", "")

                "$integerFormatted.$decimalPart"
            } else {
                // For whole numbers, use the conversion function
                try {
                    formatNumber(
                        number = convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble(),
                        formatType = numberFormat,
                        decimalDigits = currency.decimalDigits.toInt()
                    )
                } catch (e: Exception) {
                    formatNumber(0.0, numberFormat, currency.decimalDigits.toInt())
                }
            }
            amountTempFormatted.value = displayValue
        } else {
            amountTempFormatted.value = formatNumber(0.0, numberFormat, currency.decimalDigits.toInt())
        }
    }

    fun openSheet() {
        // Better initialization - convert current amount to internal format
        val initialValue = (amountCurrent.value * 10.0.pow(currency.decimalDigits.toDouble())).toLong().toString()
        amountTemp.value = if (initialValue == "0") "0" else initialValue

        // Reset calculator state when opening
        currentOperator.value = ""
        previousNumber.value = ""
        isCalculating.value = false

        showSheet.value = true
    }

    fun changeAmount(value: String) {
        when {
            amountTemp.value == "0" && value != "0" -> {
                amountTemp.value = value
            }
            amountTemp.value == "0" && value == "0" -> return // Don't add multiple zeros
            else -> amountTemp.value += value
        }
    }

    fun confirmAmount() {
        // Convert final result to proper format for saving
        val finalAmount = if (amountTemp.value.contains(".")) {
            amountTemp.value.toDoubleOrNull() ?: 0.0
        } else {
            convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble()
        }
        updateAmount(finalAmount)
        amountCurrent.value = finalAmount
        showSheet.value = false
    }

    fun performCalculation() {
        val prev = if (previousNumber.value.contains(".")) {
            previousNumber.value.toDoubleOrNull() ?: 0.0
        } else {
            convertStringToDecimal(previousNumber.value, currency.decimalDigits.toInt()).toDouble()
        }

        val curr = if (amountTemp.value.contains(".")) {
            amountTemp.value.toDoubleOrNull() ?: 0.0
        } else {
            convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble()
        }

        val result = when (currentOperator.value) {
            "+" -> prev + curr
            "-" -> prev - curr
            "×" -> prev * curr
            "÷" -> if (curr != 0.0) prev / curr else 0.0
            else -> curr
        }

        // Convert result back to appropriate format
        if (result % 1.0 == 0.0) {
            // Whole number
            amountTemp.value = (result * 10.0.pow(currency.decimalDigits.toDouble())).toLong().toString()
        } else {
            // Decimal number - keep as decimal
            amountTemp.value = result.toString()
        }
    }

    fun touchButtonKeyboard(value: String) {
        when (value) {
            "C" -> {
                amountTemp.value = "0"
                currentOperator.value = ""
                previousNumber.value = ""
                isCalculating.value = false
            }
            "±" -> {
                val current = if (amountTemp.value.contains(".")) {
                    // Handle decimal numbers directly
                    amountTemp.value.toDoubleOrNull() ?: 0.0
                } else {
                    // Handle internal format numbers
                    convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble()
                }
                val negated = -current

                // Keep the same format as the original
                if (amountTemp.value.contains(".")) {
                    amountTemp.value = negated.toString()
                } else {
                    amountTemp.value = (negated * 10.0.pow(currency.decimalDigits.toDouble())).toLong().toString()
                }
            }
            "%" -> {
                if (previousNumber.value.isNotEmpty() && currentOperator.value.isNotEmpty()) {
                    // Case 1: Operation is active (e.g., "1000 - 15%")
                    // Calculate percentage of the previous number and perform the operation
                    val prev = if (previousNumber.value.contains(".")) {
                        previousNumber.value.toDoubleOrNull() ?: 0.0
                    } else {
                        convertStringToDecimal(previousNumber.value, currency.decimalDigits.toInt()).toDouble()
                    }

                    val curr = if (amountTemp.value.contains(".")) {
                        amountTemp.value.toDoubleOrNull() ?: 0.0
                    } else {
                        convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble()
                    }

                    // Calculate percentage of previous number
                    val percentageValue = prev * (curr / 100)

                    // Perform the operation with the percentage value
                    val result = when (currentOperator.value) {
                        "+" -> prev + percentageValue
                        "-" -> prev - percentageValue
                        "×" -> prev * (curr / 100)  // For multiplication, use the percentage directly
                        "÷" -> if (curr != 0.0) prev / (curr / 100) else 0.0
                        else -> percentageValue
                    }

                    // Set result and clear operation state
                    if (result % 1.0 == 0.0) {
                        amountTemp.value = (result * 10.0.pow(currency.decimalDigits.toDouble())).toLong().toString()
                    } else {
                        amountTemp.value = result.toString()
                    }

                    currentOperator.value = ""
                    previousNumber.value = ""
                    isCalculating.value = false
                } else {
                    // Case 2: No operation active (e.g., "1000 % 15")
                    // Calculate percentage of current number (this acts like "1000 × 15%")
                    if (previousNumber.value.isNotEmpty()) {
                        val prev = if (previousNumber.value.contains(".")) {
                            previousNumber.value.toDoubleOrNull() ?: 0.0
                        } else {
                            convertStringToDecimal(previousNumber.value, currency.decimalDigits.toInt()).toDouble()
                        }

                        val curr = if (amountTemp.value.contains(".")) {
                            amountTemp.value.toDoubleOrNull() ?: 0.0
                        } else {
                            convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble()
                        }

                        // Calculate prev × curr%
                        val result = prev * (curr / 100)

                        if (result % 1.0 == 0.0) {
                            amountTemp.value = (result * 10.0.pow(currency.decimalDigits.toDouble())).toLong().toString()
                        } else {
                            amountTemp.value = result.toString()
                        }

                        previousNumber.value = ""
                    } else {
                        // Just convert current number to percentage (divide by 100)
                        val current = if (amountTemp.value.contains(".")) {
                            amountTemp.value.toDoubleOrNull() ?: 0.0
                        } else {
                            convertStringToDecimal(amountTemp.value, currency.decimalDigits.toInt()).toDouble()
                        }
                        val percentage = current / 100

                        if (percentage % 1.0 == 0.0) {
                            amountTemp.value = (percentage * 10.0.pow(currency.decimalDigits.toDouble())).toLong().toString()
                        } else {
                            amountTemp.value = percentage.toString()
                        }
                    }
                }
            }
            "+", "-", "×", "÷" -> {
                if (previousNumber.value.isNotEmpty() && currentOperator.value.isNotEmpty() && !isCalculating.value) {
                    // Perform pending calculation
                    performCalculation()
                }
                previousNumber.value = amountTemp.value
                currentOperator.value = value
                isCalculating.value = true
            }
            "=" -> {
                if (previousNumber.value.isNotEmpty() && currentOperator.value.isNotEmpty() && !isCalculating.value) {
                    performCalculation()
                    currentOperator.value = ""
                    previousNumber.value = ""
                    isCalculating.value = false
                } else {
                    confirmAmount()
                }
            }
            "backspace" -> {
                if (amountTemp.value.length > 1) {
                    val newValue = amountTemp.value.dropLast(1)
                    amountTemp.value = newValue
                } else {
                    amountTemp.value = "0"
                }
            }
            else -> { // Numbers 0-9
                if (isCalculating.value) {
                    amountTemp.value = value
                    isCalculating.value = false
                } else {
                    changeAmount(value)
                }
            }
        }
    }

    CustomBox(
        margin = margin,
        padding = PaddingValues(16.dp),
        widthFraction = 1f,
        maxWidthDp = 10000.dp,
        color = color,
        contentAlignment = Alignment.TopStart,
        onClick = { openSheet() }
    ) {
        Column() {
            Text(
                text = stringResource(Res.string.amount),
                style = MaterialTheme.typography.bodySmall
            )
            AdaptiveText(
                text = "${currency.symbolNative} ${amountFormatted.value}",
                color = color
            )
        }
    }

    if (showSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showSheet.value = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            // Sheet content
            CustomColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AdaptiveText(
                    text = "${currency.symbolNative} ${amountTempFormatted.value}",
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
                            onClick = { touchButtonKeyboard(value) }
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
                                    if (isCalculating.value)
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