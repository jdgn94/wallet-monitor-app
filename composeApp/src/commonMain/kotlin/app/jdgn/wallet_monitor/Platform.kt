package app.jdgn.wallet_monitor

enum class Platform {
    ANDROID,
    IOS
}

expect val currentPlatform: Platform