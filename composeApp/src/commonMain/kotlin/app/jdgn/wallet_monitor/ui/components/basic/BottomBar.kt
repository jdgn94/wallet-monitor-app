package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

sealed class Screen(val title: String, val icon: DrawableResource) {
    object Home : Screen("Home", LocalResource.Icons.Outlined.home)
    object Accounts : Screen("Accounts", LocalResource.Icons.Outlined.home)
    object Analytics : Screen("Analytics", LocalResource.Icons.Outlined.home)
    object More : Screen("More", LocalResource.Icons.Outlined.home)
}

@Composable
fun BottomBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        Screen.Home,
        Screen.Accounts,
        Screen.Analytics,
        Screen.More
    )

    NavigationBar(
        containerColor = Color(0xFFF0E8F8), // Color de fondo claro
        tonalElevation = 0.dp // Elimina la sombra si no la necesitas
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painterResource(item.icon), contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}