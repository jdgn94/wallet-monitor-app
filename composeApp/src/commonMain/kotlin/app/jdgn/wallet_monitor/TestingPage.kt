package app.jdgn.wallet_monitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.wallet_monitor.shared.GlobalStateManager
import app.wallet_monitor.shared.UserPreferences
import app.wallet_monitor.shared.viewModel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun testingPage() {
//    val viewModel = koinViewModel<MainViewModel>()
//    val timer by viewModel.timer.collectAsState()
//
//    println("Tengo el global state en el testing page? ${GlobalStateManager.userPreferences?.getPrefs()}")
    val userPreferences = GlobalStateManager.userPreferences!!
    val darkMode = userPreferences.getBoolean("dark_mode").collectAsState(false).value
    val dynamicColor = userPreferences.getBoolean("dynamic_color").collectAsState(false).value
    println(darkMode)
    val scope = rememberCoroutineScope()

    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
    //        Text(
    //            timer.toString()
    //        )
    //        Text("Last timer: $lastTimer")
            Row {
                Text(if (darkMode)"Modo Oscuro" else "Modo Claro")
                Switch(
                    checked = darkMode,
                    onCheckedChange = {
                        scope.launch {
                            userPreferences.setBoolean(
                                key = "dark_mode",
                                value = !darkMode
                            )
                        }
                    }
                )
            }
            Row {
                Text(if (dynamicColor)"Colores dinamicos" else "Colores del app")
                Switch(
                    checked = dynamicColor,
                    onCheckedChange = {
                        scope.launch {
                            userPreferences.setBoolean(
                                key = "dynamic_color",
                                value = !dynamicColor
                            )
                        }
                    }
                )
            }
        }
    }
}
