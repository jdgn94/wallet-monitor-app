package app.wallet_monitor.db

import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyType(
    val id: Int,
    val name: String,
    val createdAt: DateTimeUnit
)
