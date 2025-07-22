package app.wallet_monitor.shared.db.seeders

import app.wallet_monitor.shared.repository.CurrencyRepository
import app.wallet_monitor.shared.repository.CurrencyTypeRepository
import app.wallet_monitor.shared.utils.ResourceLoader

class Seeders(
    private val resourceLoader: ResourceLoader,
    private val currencyRepository: CurrencyRepository,
    private val currencyTypeRepository: CurrencyTypeRepository
) {
    suspend fun importCurrencyTypeSeedData() {
        val jsonContent = resourceLoader.asString("currency_type_seed.json")
        currencyTypeRepository.importCurrencyTypeFromJson(jsonContent)
    }

    suspend fun importCurrencySeedData() {
        val jsonContent = resourceLoader.asString("currency_seed.json")
        currencyRepository.importCurrenciesFromJson(jsonContent)
    }
}
