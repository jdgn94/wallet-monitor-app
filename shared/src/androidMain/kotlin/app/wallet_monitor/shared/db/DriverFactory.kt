package app.wallet_monitor.shared.db;

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.wallet_monitor.db.WalletMonitorDB
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        val dbDir = context.getExternalFilesDir(null)
        val databaseFile = File(dbDir, DB_NAME)
        return AndroidSqliteDriver(
            schema = WalletMonitorDB.Schema,
            context = context,
            name = databaseFile.absolutePath,
        )
    }
}