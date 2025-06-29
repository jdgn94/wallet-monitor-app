package app.wallet_monitor.shared

import app.wallet_monitor.shared.viewModel.MainViewModel
import app.wallet_monitor.shared.viewModel.TestingViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModules = appModule

val dataModules = module {
}

val viewModelModules = module {
    if (currentPlatform == Platform.ANDROID) {
        factoryOf(::MainViewModel)
        factoryOf(::TestingViewModel)
    } else {
        singleOf(::MainViewModel)
        singleOf(::TestingViewModel)
    }
}

expect val nativeModules: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModules, dataModules, viewModelModules, nativeModules)
    }
}