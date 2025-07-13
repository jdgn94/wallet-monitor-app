package app.wallet_monitor.shared.db;

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.wallet_monitor.db.WalletMonitorDB

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = WalletMonitorDB.Schema,
            context = context,
            name ="wallet_monitor.v0.db",
        );
    }
}