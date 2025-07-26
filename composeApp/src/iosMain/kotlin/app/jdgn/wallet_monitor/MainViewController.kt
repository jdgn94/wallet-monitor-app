package app.jdgn.wallet_monitor

import androidx.compose.ui.window.ComposeUIViewController
import app.wallet_monitor.shared.initKoin

fun MainViewController() = ComposeUIViewController(
) {
    initKoin()
    App()
}