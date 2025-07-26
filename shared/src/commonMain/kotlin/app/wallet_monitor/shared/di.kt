package app.wallet_monitor.shared

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.wallet_monitor.db.WalletMonitorDB
import app.wallet_monitor.shared.repository.DataInitializer
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.wallet_monitor.shared.viewModel.LanguageViewModel
import app.wallet_monitor.shared.viewModel.UserPreferenceViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModules = appModule

val dataModules = module {
    single(createdAtStart = true) { DataInitializer() }
}

val viewModelModules = module {
    viewModelOf(::CurrencyViewModel)
    viewModelOf(::LanguageViewModel)
    viewModelOf(::UserPreferenceViewModel)
}

expect val nativeModules: Module

val databaseModules = module {
    single { WalletMonitorDB(get()) }
    single { get<WalletMonitorDB>().currencyTypeQueries }
    single { get<WalletMonitorDB>().currencyQueries }
    single { get<WalletMonitorDB>().exchangeRateQueries }
    single { UserPreferences(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModules, nativeModules, databaseModules, dataModules, viewModelModules)
    }
}
