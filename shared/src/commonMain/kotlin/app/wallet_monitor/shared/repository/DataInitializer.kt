package app.wallet_monitor.shared.repository

import app.wallet_monitor.shared.UserPreferences
import app.wallet_monitor.shared.networking.HttpController
import app.wallet_monitor.shared.utils.ResourceLoader

import org.koin.core.component.KoinComponent
import app.wallet_monitor.shared.db.seeders.Seeders
import app.walletmonitor.db.v0.AccountQueries
import app.walletmonitor.db.v0.BankQueries
import app.walletmonitor.db.v0.CurrencyQueries
import app.walletmonitor.db.v0.CurrencyTypeQueries
import app.walletmonitor.db.v0.ExchangeRateQueries
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.getValue

@Suppress("UNCHECKED_CAST")
class DataInitializer: KoinComponent {
    // Inject generated SQLDelight query objects
    private val currencyTypes: CurrencyTypeQueries by inject()
    private val currencies: CurrencyQueries by inject()
    private val exchangeRates: ExchangeRateQueries by inject()
    private val banks: BankQueries by inject()
    private val accounts: AccountQueries by inject()

    // Inject user preferences
    val userPreferences: UserPreferences by inject()

    // http
    private val httpController: HttpController = HttpController()

    // Immediately launch a background coroutine
    init {
        CoroutineScope(Dispatchers.Default).launch {
            seedIfNeeded()
        }
    }

    private suspend fun seedIfNeeded() {
        println("Init database")
        val types = currencyTypes.getAll().executeAsList()
        println("types on db: $types")
        if (types.count() == 0) {
            println("need insert seeds")
            getInitialData()
        }
    }

    private suspend fun getInitialData() {
        println("calling to api")
//        httpController.getRequest(url = "/currency_type")
//            .onSuccess {
//                println("this is my calling result: $it")
//            }
//            .onError {
//                println("error on get data: $it")
                insertCurrencies()
//            }
    }

    private suspend fun insertCurrencies() {
        val resourceLoader = ResourceLoader
        val currencyProvider = CurrencyRepository(currencies)
        val currencyTypeProvider = CurrencyTypeRepository(currencyTypes)
        val importer = Seeders(resourceLoader, currencyProvider, currencyTypeProvider)
        try {
            importer.importCurrencyTypeSeedData()
            importer.importCurrencySeedData()
            println("Currency data imported successfully")
        } catch (e: Exception) {
            println("Failed to import currency data: ${e.message}")
        }
    }
}
