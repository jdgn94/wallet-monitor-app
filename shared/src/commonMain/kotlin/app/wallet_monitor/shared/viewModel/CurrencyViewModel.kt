package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.wallet_monitor.db.WalletMonitorDB
import app.walletmonitor.db.v0.Currencies
import app.walletmonitor.db.v0.CurrencyQueries
import org.koin.core.component.inject
import kotlin.getValue

class CurrencyViewModel(
    private val currencyQueries: CurrencyQueries
): ViewModel() {
    private var _currencies: List<Currencies> = listOf()

    suspend fun getCurrencies(): List<Currencies> {
        if (!_currencies.isEmpty())
            return _currencies

        currencyQueries.getAllByType(currencyTypeId = 1.toLong()).executeAsList()
            .also { _currencies = it }
        return _currencies
    }
}
