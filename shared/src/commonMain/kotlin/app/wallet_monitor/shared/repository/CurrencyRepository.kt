package app.wallet_monitor.shared.repository

import app.wallet_monitor.shared.model.CurrencyModel
import app.walletmonitor.db.v0.CurrencyQueries
import kotlinx.serialization.json.Json

class CurrencyRepository(private val currencyProvider: CurrencyQueries) {
    fun importCurrenciesFromJson(jsonContent: String) {
        val json = Json { ignoreUnknownKeys = true }
        val currencies = json.decodeFromString<List<CurrencyModel>>(jsonContent)

        insertCurrenciesToDatabase(currencies)
    }

    private fun insertCurrenciesToDatabase(currencies: List<CurrencyModel>) {
        currencyProvider.transaction {
            currencies.forEach { currency ->
                val currencyInserted = currencyProvider.getOneByCode(currency.code).executeAsOneOrNull()

                if (currencyInserted == null) {
                    currencyProvider.insert(
                        name = currency.name,
                        symbol = currency.symbol,
                        symbolNative = currency.symbol_native,
                        code = currency.code,
                        decimalDigits = currency.decimal_digits.toLong(),
                        rounding = currency.rounding.toLong(),
                        currencyTypeId = currency.type.toLong(),
                        countries = currency.countries,
                    )
                }
            }
        }
    }
}