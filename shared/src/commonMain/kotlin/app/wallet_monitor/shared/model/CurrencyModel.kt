package app.wallet_monitor.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyModel(
    val id: Int,
    val code: String,
    val symbol: String,
    val symbol_native: String,
    val name: String,
    val decimal_digits: Int,
    val rounding: Int,
    val type: Int,
    val countries: String,
    val created_at: String
)