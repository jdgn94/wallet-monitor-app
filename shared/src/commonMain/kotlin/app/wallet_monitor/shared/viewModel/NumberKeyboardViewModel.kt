package app.wallet_monitor.shared.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import app.wallet_monitor.shared.utils.convertStringToDecimal
import app.wallet_monitor.shared.utils.formatNumber
import app.walletmonitor.db.v0.Currencies
import kotlin.math.pow

class NumberKeyboardViewModel {

    // Estados públicos para la UI
    private val _amountCurrent = mutableStateOf(0.0)
    val amountCurrent: State<Double> = _amountCurrent

    private val _amountFormatted = mutableStateOf("")
    val amountFormatted: State<String> = _amountFormatted

    private val _amountTemp = mutableStateOf("")
    val amountTemp: State<String> = _amountTemp

    private val _amountTempFormatted = mutableStateOf("")
    val amountTempFormatted: State<String> = _amountTempFormatted

    private val _isSheetVisible = mutableStateOf(false)
    val isSheetVisible: State<Boolean> = _isSheetVisible

    private val _isCalculating = mutableStateOf(false)
    val isCalculating: State<Boolean> = _isCalculating

    // Estados privados para la lógica interna
    private var currentOperator by mutableStateOf("")
    private var _previousNumber by mutableStateOf("")
    var previousNumber: String = _previousNumber

    private lateinit var currentCurrency: Currencies
    private val numberFormat = "dot_comma"

    fun initialize(initialAmount: Double, currency: Currencies) {
        currentCurrency = currency
        _amountCurrent.value = initialAmount
        updateAmountFormatted()
    }

    private fun updateAmountFormatted() {
        _amountFormatted.value = formatNumber(
            number = _amountCurrent.value,
            formatType = numberFormat,
            decimalDigits = currentCurrency.decimalDigits.toInt()
        )
    }

    private fun updateTempFormatted() {
        if (_amountTemp.value.isNotEmpty()) {
            val displayValue = if (_amountTemp.value.contains(".")) {
                val parts = _amountTemp.value.split(".")
                val integerPart = parts[0].ifEmpty { "0" }
                val decimalPart = if (parts.size > 1) parts[1] else ""
                val integerFormatted = formatNumber(
                    number = integerPart.toDoubleOrNull() ?: 0.0,
                    formatType = numberFormat,
                    decimalDigits = 0
                ).replace(".00", "").replace(",00", "")
                "$integerFormatted.$decimalPart"
            } else {
                try {
                    formatNumber(
                        number = convertStringToDecimal(_amountTemp.value, currentCurrency.decimalDigits.toInt()).toDouble(),
                        formatType = numberFormat,
                        decimalDigits = currentCurrency.decimalDigits.toInt()
                    )
                } catch (e: Exception) {
                    formatNumber(0.0, numberFormat, currentCurrency.decimalDigits.toInt())
                }
            }
            _amountTempFormatted.value = displayValue
        } else {
            _amountTempFormatted.value = formatNumber(0.0, numberFormat, currentCurrency.decimalDigits.toInt())
        }
    }

    fun openSheet() {
        // Inicialización y reseteo de la calculadora
        val initialValue = (_amountCurrent.value * 10.0.pow(currentCurrency.decimalDigits.toDouble())).toLong().toString()
        _amountTemp.value = if (initialValue == "0") "0" else initialValue
        currentOperator = ""
        _previousNumber = ""
        _isCalculating.value = false
        updateTempFormatted()
        _isSheetVisible.value = true
    }

    fun closeSheet() {
        _isSheetVisible.value = false
    }

    fun handleKeyboardInput(value: String, onAmountConfirmed: (Double) -> Unit) {
        when (value) {
            "C" -> resetCalculator()
            "±" -> negateAmount()
            "%" -> calculatePercentage()
            "+", "-", "×", "÷" -> setOperator(value)
            "=" -> handleEqualSign(onAmountConfirmed)
            "backspace" -> removeLastDigit()
            else -> appendNumber(value)
        }
        // Actualizar el estado formateado después de cada cambio
        updateTempFormatted()
    }

    private fun resetCalculator() {
        _amountTemp.value = "0"
        currentOperator = ""
        _previousNumber = ""
        _isCalculating.value = false
    }

    private fun negateAmount() {
        val current = if (_amountTemp.value.contains(".")) {
            _amountTemp.value.toDoubleOrNull() ?: 0.0
        } else {
            convertStringToDecimal(_amountTemp.value, currentCurrency.decimalDigits.toInt()).toDouble()
        }
        val negated = -current

        if (_amountTemp.value.contains(".")) {
            _amountTemp.value = negated.toString()
        } else {
            _amountTemp.value = (negated * 10.0.pow(currentCurrency.decimalDigits.toDouble())).toLong().toString()
        }
    }

    private fun calculatePercentage() {
        if (_previousNumber.isNotEmpty() && currentOperator.isNotEmpty() && !_isCalculating.value) {
            // ... (Lógica de porcentaje)
        } else if (_previousNumber.isNotEmpty()) {
            // ... (Lógica de porcentaje)
        } else {
            val current = if (_amountTemp.value.contains(".")) {
                _amountTemp.value.toDoubleOrNull() ?: 0.0
            } else {
                convertStringToDecimal(_amountTemp.value, currentCurrency.decimalDigits.toInt()).toDouble()
            }
            val percentage = current / 100

            if (percentage % 1.0 == 0.0) {
                _amountTemp.value = (percentage * 10.0.pow(currentCurrency.decimalDigits.toDouble())).toLong().toString()
            } else {
                _amountTemp.value = percentage.toString()
            }
        }
        // Lógica de porcentaje completa...
    }

    private fun setOperator(operator: String) {
        if (_previousNumber.isNotEmpty() && currentOperator.isNotEmpty() && !_isCalculating.value) {
            performCalculation()
        }
        _previousNumber = _amountTemp.value
        currentOperator = operator
        _isCalculating.value = true
    }

    private fun handleEqualSign(onAmountConfirmed: (Double) -> Unit) {
        if (_previousNumber.isNotEmpty() && currentOperator.isNotEmpty() && !_isCalculating.value) {
            performCalculation()
            currentOperator = ""
            _previousNumber = ""
            _isCalculating.value = false
        } else {
            val finalAmount = if (_amountTemp.value.contains(".")) {
                _amountTemp.value.toDoubleOrNull() ?: 0.0
            } else {
                convertStringToDecimal(_amountTemp.value, currentCurrency.decimalDigits.toInt()).toDouble()
            }
            _amountCurrent.value = finalAmount
            onAmountConfirmed(finalAmount)
            updateAmountFormatted()
            closeSheet()
        }
    }

    private fun removeLastDigit() {
        if (_amountTemp.value.length > 1) {
            _amountTemp.value = _amountTemp.value.dropLast(1)
        } else {
            _amountTemp.value = "0"
        }
    }

    private fun appendNumber(value: String) {
        if (_isCalculating.value) {
            _amountTemp.value = value
            _isCalculating.value = false
        } else {
            if (_amountTemp.value == "0" && value != "0") {
                _amountTemp.value = value
            } else if (_amountTemp.value != "0") {
                _amountTemp.value += value
            }
        }
    }

    private fun performCalculation() {
        // Lógica de cálculo...
        val prev = if (_previousNumber.contains(".")) {
            _previousNumber.toDoubleOrNull() ?: 0.0
        } else {
            convertStringToDecimal(_previousNumber, currentCurrency.decimalDigits.toInt()).toDouble()
        }

        val curr = if (_amountTemp.value.contains(".")) {
            _amountTemp.value.toDoubleOrNull() ?: 0.0
        } else {
            convertStringToDecimal(_amountTemp.value, currentCurrency.decimalDigits.toInt()).toDouble()
        }

        val result = when (currentOperator) {
            "+" -> prev + curr
            "-" -> prev - curr
            "×" -> prev * curr
            "÷" -> if (curr != 0.0) prev / curr else 0.0
            else -> curr
        }

        if (result % 1.0 == 0.0) {
            _amountTemp.value = (result * 10.0.pow(currentCurrency.decimalDigits.toDouble())).toLong().toString()
        } else {
            _amountTemp.value = result.toString()
        }
    }
}
