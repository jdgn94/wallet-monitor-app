package app.wallet_monitor.shared.utils

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object ResourceLoader {
    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun asString(resourcePath: String): String {
        // Get the bundle that contains the resource
        val bundle = NSBundle.mainBundle
        // Get the path to the resource
        val path = bundle.pathForResource(
            resourcePath.substringBeforeLast("."),
            resourcePath.substringAfterLast(".")
        ) ?: throw IllegalArgumentException("Resource $resourcePath not found")

        return NSString.stringWithContentsOfFile(
            path,
            encoding = NSUTF8StringEncoding,
            error = null
        ) as String
    }
}