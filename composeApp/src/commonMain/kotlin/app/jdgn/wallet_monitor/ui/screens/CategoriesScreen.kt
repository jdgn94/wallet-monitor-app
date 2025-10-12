package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.jdgn.wallet_monitor.ui.LocalResource
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.CustomMessage
import app.jdgn.wallet_monitor.ui.components.basic.MessageType
import app.jdgn.wallet_monitor.ui.components.basic.TopBar
import app.jdgn.wallet_monitor.ui.components.composed.CategoryComponent
import app.wallet_monitor.shared.viewModel.CategoryViewModel
import app.walletmonitor.db.v0.Categories
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.categories
import walletmonitor.composeapp.generated.resources.warning
import walletmonitor.composeapp.generated.resources.warningCategory

@Composable
fun CategoriesScreen(navController: NavHostController) {
    val viewModelCategories = koinViewModel< CategoryViewModel>()
    val allCategories = remember { mutableStateOf<List<Categories>>(listOf()) }
    val page = remember { mutableStateOf(1) }

    // Listen for navigation changes and refresh data
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        allCategories.value = viewModelCategories.getAll()
//        allCategories.value = viewModelCategories.getAllPaginate("", page.value)
        println("Busqueda de todas categorias, cantidad ${allCategories.value.count()}")
    }

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = stringResource(Res.string.categories),
                backButton = true,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateTo("category/") }
            ) {
                Icon(
                    painter = painterResource(LocalResource.Icons.plus),
                    contentDescription = "add",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { padding ->
        CustomColumn(
            modifier = Modifier.fillMaxWidth().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (allCategories.value.isEmpty()) {
                CustomMessage(
                    icon = painterResource(LocalResource.Icons.Outlined.warning),
                    title = stringResource(Res.string.warning),
                    message = stringResource(Res.string.warningCategory),
                    type = MessageType.WARNING
                )
                HorizontalDivider(color = Color.Transparent)
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                allCategories.value.map { category ->
                    CategoryComponent(navController, category)
                }
            }
        }
    }
}
