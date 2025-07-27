package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import app.wallet_monitor.shared.UserPreferences
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class UserPreferenceViewModel(
    private val userPreferences: UserPreferences
): ViewModel() {
    fun getString(key: String) = runBlocking {
        userPreferences.getString(key).firstOrNull()
    }

    fun setString(key: String, value: String) = runBlocking {
        userPreferences.setString(key, value)
    }

}