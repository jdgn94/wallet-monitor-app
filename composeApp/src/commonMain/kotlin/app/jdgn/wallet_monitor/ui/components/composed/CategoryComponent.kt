package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.utils.hexStringToColor
import app.jdgn.wallet_monitor.utils.imageByString
import app.walletmonitor.db.v0.Categories
import org.jetbrains.compose.resources.painterResource

@Composable
fun CategoryComponent(navController: NavHostController, category: Categories) {
    CustomBox(
        padding = PaddingValues(8.dp),
        margin = PaddingValues(bottom = 8.dp),
        minusWidthFraction = 24.dp,
        color = hexStringToColor(category.color),
        onClick = { navController.navigate("category/${category.id}") }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(60.dp)
                .rotate(20f)
                .alpha(0.35f),
            painter = painterResource(imageByString(category.icon)),
            contentDescription = category.name,
        )
        Column(){
            Row {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(16.dp)
                        .background(color = hexStringToColor(category.color))
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = category.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Total subcategories: 0",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7F)
            )
        }
    }
}