package app.wallet_monitor.shared.db


import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.wallet_monitor.db.WalletMonitorDB

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(WalletMonitorDB.Schema, "wallet_monitor.db")
    }
}