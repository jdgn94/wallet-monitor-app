
package app.wallet_monitor.shared.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"
)
actual object ResourceLoader {
    actual suspend fun asString(resourcePath: String): String {
        return javaClass.classLoader.getResourceAsStream(resourcePath)?.use {
            it.bufferedReader().readText()
        } ?: throw IllegalArgumentException("Resource $resourcePath not found")
    }
}