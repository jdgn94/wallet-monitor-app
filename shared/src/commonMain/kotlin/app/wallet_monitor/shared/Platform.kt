package app.wallet_monitor.shared

enum class Platform {
    ANDROID,
    IOS
}

expect val currentPlatform: Platform