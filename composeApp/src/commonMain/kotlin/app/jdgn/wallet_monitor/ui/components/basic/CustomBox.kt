package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import app.jdgn.wallet_monitor.getScreenWidth

@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(),
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color? = null,
    onClick: (() -> Unit)? = null,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp,
    content: @Composable BoxScope.() -> Unit
) {
    // Get screen width
    val screenWidth = getScreenWidth()

    // Calculate width 50%
    val boxWidth = (screenWidth * widthFraction).coerceAtMost(maxWidthDp)

    Box(
        modifier = Modifier
            .width(boxWidth)
            .widthIn(max = 200.dp)
            .padding(margin)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = color,
                ambientColor = color,
            ),
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(
                width = 0.dp,
                color = Color.Transparent
            ),
            onClick = onClick ?: { },
            enabled = onClick != null,
//            stateLayerColor = color.copy(alpha = 0.12f),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = modifier
                    .background(backgroundColor?.copy(alpha = 0.2f) ?: MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(padding),
                contentAlignment = Alignment.Center,
                content = content
            )
        }
    }
}
