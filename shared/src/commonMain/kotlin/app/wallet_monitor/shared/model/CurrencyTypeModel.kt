package app.wallet_monitor.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyTypeModel(
    val id: Int,
    val name: String,
)
