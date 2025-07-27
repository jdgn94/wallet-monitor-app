package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Outlined
import app.jdgn.wallet_monitor.ui.pages.config.*
import app.wallet_monitor.shared.viewModel.AccountViewModel
import app.wallet_monitor.shared.viewModel.CurrencyViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.cash

@Preview
@Composable
fun InitialConfigScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val accountName = stringResource(Res.string.cash)
    val viewModelCurrencies = koinViewModel<CurrencyViewModel>()
    val viewModelAccounts = koinViewModel<AccountViewModel>()
    val showButton = remember { mutableStateOf(false) }

    fun activeButton() {
        showButton.value = true
    }

    val fabScale by animateFloatAsState(
        targetValue = if (showButton.value) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val fabAlpha by animateFloatAsState(
        targetValue = if (showButton.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    val fabRotation by animateFloatAsState(
        targetValue = if (showButton.value) 0f else 180f,
        animationSpec = tween(durationMillis = 500)
    )

    fun createPrimaryAccount() {
        val defaultCurrency = viewModelCurrencies.getDefaultCurrency()
        viewModelAccounts.createAccount(
            currencyId = defaultCurrency!!.id,
            amount = 0.toLong(),
            color = "#FF5C6BC0",
            name = accountName
        )

        navController.navigate("home") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { createPrimaryAccount() },
                modifier = Modifier
                    .scale(fabScale)
                    .alpha(fabAlpha)
                    .rotate(fabRotation)
                    .padding(bottom = 16.dp, end = 16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(Outlined.home),
                    contentDescription = "Continue",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState).padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            SetDefaultCurrency(changeCurrency = { activeButton() })
            SetDefaultLanguage()
            SetDefaultTheme()
        }

    }
}
