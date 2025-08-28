package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import app.jdgn.wallet_monitor.getScreenWidth

@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    pickWidth: Dp? = null,
    height: Dp? = null,
    minHeight: Dp? = null,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(),
    color: Color = MaterialTheme.colorScheme.primary,
    minusWidthFraction: Dp = 0.dp,
    backgroundColor: Color? = null,
    backgroundFullColor: Boolean = false,
    splashColor: Color = color,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp,
    contentAlignment: Alignment = Alignment.Center,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    // Get screen width
    val screenWidth = getScreenWidth()

    // Calculate width 50%
    val boxWidth = pickWidth ?: ((screenWidth * widthFraction).coerceAtMost(maxWidthDp) - minusWidthFraction)

    Box(
        modifier = Modifier
            .padding(margin)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = color,
                ambientColor = color,
            ),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)) // Equivalente a la forma de Surface
                .background(MaterialTheme.colorScheme.surface) // Equivalente al color de Surface
                .border(
                    border = BorderStroke(width = 0.dp, color = Color.Transparent),
                    shape = RoundedCornerShape(16.dp)
                ) // Equivalente al borde de Surface
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = splashColor),
                    onClick = onClick ?: { },
                    enabled = onClick != null
                )
        ) {
            Box(
                modifier = modifier
                    .let { m -> // Se usa `let` para aplicar el modificador condicionalmente
                        if (height != null) {
                            m.height(height)
                        } else if (minHeight != null) {
                            m.heightIn(min = minHeight)
                        } else {
                            m
                        }
                    }
                    .width(pickWidth ?: boxWidth)
                    .background(backgroundColor?.copy(alpha = if (backgroundFullColor) 1f else 0.2f) ?: MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(padding),
                contentAlignment = contentAlignment,
                content = content
            )
        }
    }
}
