package app.jdgn.wallet_monitor

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import app.wallet_monitor.shared.initKoin
import dataStore

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App(prefs = remember { dataStore() })
}