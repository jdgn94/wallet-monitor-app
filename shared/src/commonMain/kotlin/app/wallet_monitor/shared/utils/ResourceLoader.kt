package app.wallet_monitor.shared.utils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object ResourceLoader {
    suspend fun asString(resourcePath: String): String
}