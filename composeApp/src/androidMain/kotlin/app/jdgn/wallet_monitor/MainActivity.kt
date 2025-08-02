package app.jdgn.wallet_monitor

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import app.wallet_monitor.shared.APP_LANGUAGE_KEY
import app.wallet_monitor.shared.UserPreferences
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val userPreferences: UserPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val scope = rememberCoroutineScope()
            App(
                onLanguageChanged = {
                    recreate()
                }
            )
        }
    }

    // Override attachBaseContext to apply language selection on app start
    override fun attachBaseContext(newBase: Context) {
        val languageCode = runBlocking { userPreferences.getString(APP_LANGUAGE_KEY).firstOrNull() }
        val code = languageCode?.ifEmpty { getSystemLanguage() } ?: getSystemLanguage()
        val locale = Locale(/* language = */ code)
        val config = Configuration(newBase.resources.configuration)

        super.attachBaseContext(ContextWrapper(newBase.createConfigurationContext(config)))
        Locale.setDefault(locale)
    }
}
