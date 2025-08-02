package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Arrows
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    navController: NavHostController,
    title: String,
    backButton: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color.copy(alpha = 0.1f),
        ),
        navigationIcon = {
            if (backButton)
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(Arrows.back),
                        contentDescription = "Continue",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(24.dp)
                    )
                }
        },
        title = { Text(text = title, fontWeight = FontWeight.Bold) }
    )
}