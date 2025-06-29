package app.wallet_monitor.shared

import io.ktor.client.engine.HttpClientEngine

expect val httpClientNative: HttpClientEngine

object Constants {
    const val baseApiUrl = "http://localhost:8000/api"
    val httpClient = httpClientNative
}