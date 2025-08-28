package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.BottomBar
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.TopBar
import app.jdgn.wallet_monitor.ui.pages.home.AccountsHome
import app.jdgn.wallet_monitor.ui.pages.home.ChatsHome
import app.jdgn.wallet_monitor.ui.pages.home.IndexHome
import app.jdgn.wallet_monitor.ui.pages.home.MoreHome
@Composable
fun HomeScreen(navController: NavHostController) {
    val currentPage = remember { mutableStateOf(0) }

    fun onChangePage(page: Int) {
        currentPage.value = page
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = "Home",
                backButton = false
            )
        },
        bottomBar = {
            BottomBar(
                currentPage = currentPage.value,
                onChangePage = { onChangePage(it) }
            )
        }
    ) { padding ->
        CustomColumn(modifier = Modifier.padding(padding)) {
            Box(
                Modifier
                    .fillMaxSize()
            )
            AnimatedContent(
                targetState = currentPage.value,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut() // Animación de fundido
                },
                label = "ScreenTransition"
            ) { targetPage ->
                // El 'when' se mueve dentro de AnimatedContent
                when (targetPage) {
                    0 -> IndexHome(navController)
                    1 -> AccountsHome()
                    2 -> ChatsHome()
                    3 -> MoreHome()
                }
            }

        }

    }
}