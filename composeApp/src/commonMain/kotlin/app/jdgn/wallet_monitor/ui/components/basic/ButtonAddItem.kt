package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.getScreenWidth
import app.jdgn.wallet_monitor.ui.LocalResource.Icons
import org.jetbrains.compose.resources.painterResource

@Composable
fun ButtonAddItem(
    padding: PaddingValues = PaddingValues(0.dp),
    margin: PaddingValues = PaddingValues(0.dp),
    color: Color = MaterialTheme.colorScheme.primary,
    pickWidth: Dp? = null,
    strokeWidth: Dp = 2.dp,
    dotLength: Dp = 2.dp,
    gapLength: Dp = 4.dp,
    minusWidthFraction: Dp = 0.dp,
    maxWidthDp: Dp = 300.dp,
    widthFraction: Float = 0.5f,
    contentAlignment: Alignment = Alignment.Center,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    // Get screen width
    val screenWidth = getScreenWidth()

    // Calculate width 50%
    val boxWidth = pickWidth ?: ((screenWidth * widthFraction).coerceAtMost(maxWidthDp) - minusWidthFraction)

    Box(
        modifier = Modifier
            .padding(margin)
            .dottedBorder(
                color = color,
                strokeWidth = strokeWidth,
                dotLength = dotLength,
                gapLength = gapLength,
                shape = RoundedCornerShape(16.dp)
            )
        ,
    ) {
        Box(
            modifier = Modifier
                .width(pickWidth ?: boxWidth)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = color),
                    onClick = onClick
                )
                .padding(padding),
            contentAlignment = contentAlignment,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(Icons.plus),
                    contentDescription = "Continue",
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column {
                content()
            }
        }
    }
}
