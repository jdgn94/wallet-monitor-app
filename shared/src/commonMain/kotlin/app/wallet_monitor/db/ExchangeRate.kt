package app.wallet_monitor.db

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRate(
    val id: Int,
    val currencyId: Int,
    val exchangeRate: Float,
    val createdAt: DateTimeUnit,
    val updatedAt: DateTimeUnit,
    val currency: Currency
)
