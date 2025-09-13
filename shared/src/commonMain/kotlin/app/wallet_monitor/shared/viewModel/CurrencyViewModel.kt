package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wallet_monitor.shared.APP_CURRENCY_KEY
import app.wallet_monitor.shared.UserPreferences
import app.walletmonitor.db.v0.Currencies
import app.walletmonitor.db.v0.CurrencyQueries
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CurrencyViewModel(
    private val currencyQueries: CurrencyQueries,
    private val userPreferences: UserPreferences
): ViewModel() {
    fun getDefaultCurrency(): Currencies? {
        val currencyId = runBlocking { userPreferences.getInt(APP_CURRENCY_KEY).firstOrNull() }
        if (currencyId == null) return null
        val currency = currencyQueries.getOneById(currencyId.toLong()).executeAsOneOrNull()
        return currency
    }

    fun setDefaultCurrency(id: Long) {
        viewModelScope.launch {
            userPreferences.setInt(APP_CURRENCY_KEY, id.toInt())
        }
    }

    fun getOneById(id: Long): Currencies? {
        val currency = currencyQueries.getOneById(id).executeAsOneOrNull()
        return currency
    }

    fun getAllByNameAndType(
        name: String,
        currencyTypeId: Long,
        page: Int,
        pageSize: Int = 20
    ): List<Currencies> {
        val currencies = currencyQueries
            .getAllByNameAndType(
                value_ = "%$name%",
                value__ = "%$name%",
                value___ = "%$name%",
                value____ = "%$name%",
                currencyTypeId = currencyTypeId,
                value_____ = pageSize.toLong(),
                value______ = (page * pageSize).toLong()
            ).executeAsList()

        return currencies
    }
}
