package app.wallet_monitor.shared.db


import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.wallet_monitor.db.WalletMonitorDB

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(WalletMonitorDB.Schema, "wallet_monitor.v0.db")
    }
}