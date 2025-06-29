package app.wallet_monitor.db

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val id: Int,
    val code: String,
    val symbol: String,
    val symbolNative: String,
    val name: String,
    val decimalDigits: Int,
    val rounding: Int,
    val countries: Int,
    val currencyTypeId: Int,
    val currencyType: CurrencyType
)
