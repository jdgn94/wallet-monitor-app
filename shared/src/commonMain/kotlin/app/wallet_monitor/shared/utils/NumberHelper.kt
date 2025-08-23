package app.wallet_monitor.shared.utils

import kotlin.math.pow
import kotlin.math.round
import java.text.DecimalFormat
import kotlin.math.abs

/**
 * Format a number according to different international formats.
 *
 * @param number The number to format
 * @param formatType Format type - one of:
 *   "apostrophe_dot" -> "1'234.56"
 *   "apostrophe_comma" -> "1'234,56"
 *   "comma_dot" -> "1,234.56"
 *   "dot_comma" -> "1.234,56"
 *   "space_comma" -> "1 234,56"
 *   "space_dot" -> "1 234.56"
 * @param decimalDigits Number of decimal places (0-10)
 * @return Formatted number string
 * @throws IllegalArgumentException if decimalDigits is not between 0 and 10
 */
fun formatNumber(number: Double, formatType: String = "comma_dot", decimalDigits: Int = 2): String {
    // Validate decimal digits
    require(decimalDigits in 0..10) { "decimalDigits must be between 0 and 10" }

    // Round the number to specified decimal places
    val multiplier = 10.0.pow(decimalDigits)
    val roundedNumber = round(number * multiplier) / multiplier

    // Handle negative numbers
    val isNegative = roundedNumber < 0
    val absoluteNumber = abs(roundedNumber)

    // Split into integer and decimal parts
    val integerPart = absoluteNumber.toLong()
    val decimalPart = if (decimalDigits == 0) {
        ""
    } else {
        val decimal = absoluteNumber - integerPart
        val decimalString = (decimal * multiplier).toLong().toString().padStart(decimalDigits, '0')
        decimalString
    }

    // Determine separators based on format type
    val (thousandsSep, decimalSep) = when (formatType) {
        "apostrophe_dot" -> "'" to "."
        "apostrophe_comma" -> "'" to ","
        "comma_dot" -> "," to "."
        "dot_comma" -> "." to ","
        "space_comma" -> " " to ","
        "space_dot" -> " " to "."
        else -> throw IllegalArgumentException("Invalid format_type: $formatType")
    }

    // Add thousands separators
    val integerString = integerPart.toString()
    val formattedInteger = buildString {
        integerString.reversed().forEachIndexed { index, char ->
            if (index > 0 && index % 3 == 0) {
                insert(0, thousandsSep)
            }
            insert(0, char)
        }
    }

    // Combine parts
    val result = buildString {
        if (isNegative) append("-")
        append(formattedInteger)
        if (decimalDigits > 0) {
            append(decimalSep)
            append(decimalPart)
        }
    }

    return result
}

/**
 * Converts a string number by dividing it by 10^decimalPlaces and formatting with decimals
 * Example: "100000000" with 3 decimals -> "100000.000" (divided by 1000)
 */
fun convertStringToDecimal(input: String, decimalPlaces: Int = 2): String {
    return try {
        require(decimalPlaces in 0..10) { "Decimal places must be between 0 and 10" }

        val number = input.toLong()
        val divisor = 10.0.pow(decimalPlaces)
        val result = number / divisor

        "%.${decimalPlaces}f".format(result)
    } catch (e: NumberFormatException) {
        "0.${"0".repeat(decimalPlaces)}"
    } catch (e: IllegalArgumentException) {
        "0.${"0".repeat(decimalPlaces)}"
    }
}
//
//// Alternative using Double for better precision
//fun convertStringToDecimalDouble(input: String, decimalPlaces: Int = 2): String {
//    return try {
//        require(decimalPlaces in 0..10) { "Decimal places must be between 0 and 10" }
//
//        val number = input.toDouble()
//        val divisor = 10.0.pow(decimalPlaces)
//        val result = number / divisor
//
//        "%.${decimalPlaces}f".format(result)
//    } catch (e: NumberFormatException) {
//        "0.${"0".repeat(decimalPlaces)}"
//    }
//}
//
//// Extension function version
//fun String.toDecimalWithDivision(decimalPlaces: Int = 2): String {
//    return try {
//        require(decimalPlaces in 0..10) { "Decimal places must be between 0 and 10" }
//
//        val number = this.toDouble()
//        val divisor = 10.0.pow(decimalPlaces)
//        val result = number / divisor
//
//        "%.${decimalPlaces}f".format(result)
//    } catch (e: NumberFormatException) {
//        "0.${"0".repeat(decimalPlaces)}"
//    }
//}
//
//// Usage examples:
//fun main() {
//    println("Testing convertStringToDecimal:")
//    println(convertStringToDecimal("100000000", 3))  // "100000.000"
//    println(convertStringToDecimal("100000000", 2))  // "1000000.00"
//    println(convertStringToDecimal("10000", 4))      // "1.0000"
//    println(convertStringToDecimal("123456", 2))     // "1234.56"
//
//    println("\nTesting extension function:")
//    println("100000000".toDecimalWithDivision(3))    // "100000.000"
//    println("10000000".toDecimalWithDivision(7))     // "1.0000000"
//
//    println("\nEdge cases:")
//    println(convertStringToDecimal("0", 2))          // "0.00"
//    println(convertStringToDecimal("invalid", 2))    // "0.00"
//}
