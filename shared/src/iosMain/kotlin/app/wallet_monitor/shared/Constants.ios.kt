package app.wallet_monitor.shared

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val httpClientNative:HttpClientEngine = Darwin.create()