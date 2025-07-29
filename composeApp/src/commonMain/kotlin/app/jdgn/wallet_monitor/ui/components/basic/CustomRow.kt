package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable (RowScope.() -> Unit)
    ) {
    val scrollState = rememberScrollState()
    val edgeWidth = 16.dp
    val density = LocalDensity.current
    var edgeFraction by remember { mutableStateOf(0.1f) }

    Box{
        Row(
            modifier = Modifier.horizontalScroll(scrollState),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            Box(modifier = Modifier.width(16.dp))
            content()
            Box(modifier = Modifier.width(16.dp))
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .onGloballyPositioned { coordinates ->
                    val totalWidthPx = coordinates.size.width.toFloat()
                    val edgeWidthPx = with(density) { edgeWidth.toPx() }
                    edgeFraction = edgeWidthPx / totalWidthPx
                }
                .background(
                    brush = Brush.horizontalGradient(
                        colorStops = arrayOf(
                            0.0f to MaterialTheme.colorScheme.surface,
                            edgeFraction to Color.Transparent,
                            1f - edgeFraction to Color.Transparent,
                            1.0f to MaterialTheme.colorScheme.surface
                        )
                    )
                )
        )
    }
}