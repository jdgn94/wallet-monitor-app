package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.LocalResource
import app.wallet_monitor.shared.APP_CURRENCY_KEY
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import app.wallet_monitor.shared.viewModel.UserPreferenceViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun SplashScreen(navController: NavHostController) {
    val viewModel = koinViewModel<CurrencyViewModel>()
    val scale = remember { Animatable(1f) } // Initial scale
    val defaultCurrency = viewModel.getDefaultCurrency()


    fun initialValidations() {
        println("defaultCurrency: $defaultCurrency")
        if (defaultCurrency == null) {
            println("go to initial config")
            navController.navigate("welcome"){
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
            return
        }

        navController.navigate("home") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    LaunchedEffect(Unit) {
        // Start the scale animation (optional, but good for UX)
        launch { // Launch animation in a separate coroutine
            scale.animateTo(
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }

        // Delay for 2 seconds
        delay(2000L) // 2000 milliseconds = 2 seconds
        // Navigate to the next screen and clear the back stack
        initialValidations()
//        navController.navigate("home") { // Replace "homeScreen" with your actual route
//            popUpTo(navController.graph.startDestinationId) {
//                inclusive = true
//            }
//        }
    }

    Scaffold {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(LocalResource.Images.appIcon),
                contentDescription = "App Icon",
                modifier = Modifier.scale(scale.value).size(150.dp)
            )
        }
    }
}

