package app.wallet_monitor.shared

import app.wallet_monitor.db.WalletMonitorDB
import app.wallet_monitor.shared.repository.DataInitializer
import app.wallet_monitor.shared.viewModel.AccountViewModel
import app.wallet_monitor.shared.viewModel.BankViewModel
import app.wallet_monitor.shared.viewModel.CategoryViewModel
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.wallet_monitor.shared.viewModel.LanguageViewModel
import app.wallet_monitor.shared.viewModel.NumberKeyboardViewModel
import app.wallet_monitor.shared.viewModel.SubcategoryViewModel
import app.wallet_monitor.shared.viewModel.UserPreferenceViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
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
    viewModelOf(::AccountViewModel)
    viewModelOf(::BankViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::SubcategoryViewModel)
    factoryOf(::NumberKeyboardViewModel)
}

expect val nativeModules: Module

val databaseModules = module {
    single { WalletMonitorDB(get()) }
    single { get<WalletMonitorDB>().currencyTypeQueries }
    single { get<WalletMonitorDB>().currencyQueries }
    single { get<WalletMonitorDB>().exchangeRateQueries }
    single { get<WalletMonitorDB>().bankQueries }
    single { get<WalletMonitorDB>().accountQueries }
    single { get<WalletMonitorDB>().categoryQueries }
    single { get<WalletMonitorDB>().subcategoryQueries }
    single { UserPreferences(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModules, nativeModules, databaseModules, dataModules, viewModelModules)
    }
}
