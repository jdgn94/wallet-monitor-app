package app.wallet_monitor.shared

import app.wallet_monitor.db.WalletMonitorDB
import app.wallet_monitor.shared.repository.DataInitializer
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModules = appModule

val dataModules = module {
    single(createdAtStart = true) { DataInitializer() }
}

val viewModelModules = module {
    if (currentPlatform == Platform.ANDROID) {
        viewModelOf(::CurrencyViewModel)
    } else {
        singleOf(::CurrencyViewModel)
    }
}

expect val nativeModules: Module

val databaseModules = module {
    single { WalletMonitorDB(get()) }
    single { get<WalletMonitorDB>().currencyTypeQueries }
    single { get<WalletMonitorDB>().currencyQueries }
    single { get<WalletMonitorDB>().exchangeRateQueries }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModules, nativeModules, databaseModules, dataModules, viewModelModules)
    }
}
