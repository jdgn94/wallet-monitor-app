package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path

fun Modifier.dottedBorder(
    color: Color,
    strokeWidth: Dp = 1.dp,
    dotLength: Dp = 2.dp,
    gapLength: Dp = 4.dp,
    shape: Shape = RectangleShape
): Modifier = this.then(
    drawBehind {
        val outline = shape.createOutline(size, layoutDirection, this)
        val path = when (outline) {
            is Outline.Generic -> outline.path
            is Outline.Rectangle -> {
                Path().apply { addRect(outline.rect) }
            }
            is Outline.Rounded -> {
                Path().apply { addRoundRect(outline.roundRect) }
            }
            else -> Path()
        }
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dotLength.toPx(), gapLength.toPx()), 0f
            )
        )
        drawPath(
            path = path,
            color = color,
            style = stroke
        )
    }
)