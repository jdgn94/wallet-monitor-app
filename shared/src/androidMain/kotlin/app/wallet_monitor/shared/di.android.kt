package app.wallet_monitor.shared

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.wallet_monitor.shared.db.DriverFactory
import org.koin.dsl.module

actual val nativeModules = module {
    single { DriverFactory(get<Context>()) }
    single<SqlDriver> { get<DriverFactory>().createDriver() }
}