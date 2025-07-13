package app.wallet_monitor.shared.repository

import app.wallet_monitor.shared.model.CurrencyTypeModel
import app.walletmonitor.db.v0.CurrencyTypeQueries
import kotlinx.serialization.json.Json
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CurrencyTypeRepository(private val currencyTypeProvider: CurrencyTypeQueries) {
    fun importCurrencyTypeFromJson(jsonContent: String) {
        val json = Json { ignoreUnknownKeys = true }
        val currencyTypes = json.decodeFromString<List<CurrencyTypeModel>>(jsonContent)

        insertCurrencyTypesToDatabase(currencyTypes)
    }

    @OptIn(ExperimentalTime::class)
    fun insertCurrencyTypesToDatabase(currencyTypes: List<CurrencyTypeModel>) {
        currencyTypeProvider.transaction{
            currencyTypes.forEach { currencyType ->
                val currencyTypeInserted = currencyTypeProvider.getOneByName(currencyType.name).executeAsOneOrNull()

                if (currencyTypeInserted != null) {
                    currencyTypeProvider.insert(
                        name = currencyType.name,
                        createdAt = Clock.System.now().toString()
                    )
                }
            }
        }
    }
}