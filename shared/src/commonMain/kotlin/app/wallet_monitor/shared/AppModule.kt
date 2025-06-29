package app.wallet_monitor.shared

import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("baseUrl")) { "http://localhost:8000/api" }
}