package app.wallet_monitor.shared

import io.ktor.client.engine.HttpClientEngine

expect val httpClientNative: HttpClientEngine

object Constants {
    const val apiUrl = "http://192.168.1.28:8000/api"
    val httpClient = httpClientNative
}