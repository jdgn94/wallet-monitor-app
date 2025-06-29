package app.wallet_monitor.shared

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual val httpClientNative: HttpClientEngine = OkHttp.create()