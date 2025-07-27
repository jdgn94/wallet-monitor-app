package app.wallet_monitor.shared

object GlobalStateManager {
    var userPreferences: UserPreferences? = null
    var languageChange: () -> Unit = {}
    var changeTheme: () -> Unit = {}
}