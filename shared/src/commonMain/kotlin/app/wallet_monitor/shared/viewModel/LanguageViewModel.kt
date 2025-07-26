package app.wallet_monitor.shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wallet_monitor.shared.APP_LANGUAGE_KEY
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.UserPreferences
import app.wallet_monitor.shared.model.LanguageModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LanguageViewModel(
    private val userPreferences: UserPreferences
): ViewModel() {
    private val languageModels = listOf(
        LanguageModel("", "", ""),
        LanguageModel("en", "English", "en-us"),
        LanguageModel("es", "Español", "es-lat")
    )

    fun getLanguages(): List<LanguageModel> {
        return languageModels
    }

    fun getDefaultLanguage(): LanguageModel {
        val languageCode = runBlocking { userPreferences.getString(APP_LANGUAGE_KEY).firstOrNull() }
        val language = languageModels.find { it.id == languageCode }
        println("get lang: $language")

        if (language != null) return language
        return languageModels[0]
    }

    fun setDefaultLanguage(languageModel: LanguageModel) {
        val lang = languageModel.id
        println("saving lang: $lang")
        viewModelScope.launch {
            userPreferences.setString(APP_LANGUAGE_KEY, lang)

            delay(100)
            GlobalStateManager.languageChange()
        }
    }
}