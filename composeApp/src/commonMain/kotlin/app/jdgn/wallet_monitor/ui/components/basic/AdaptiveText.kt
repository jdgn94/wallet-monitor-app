package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AdaptiveText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    maxLines: Int = 1,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
    textAlign: TextAlign? = TextAlign.Center,
    minFontSize: TextUnit = 8.sp,
    maxFontSize: TextUnit = style.fontSize,
    stepSize: TextUnit = 1.sp
) {
    var fontSize by remember { mutableStateOf(maxFontSize) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        style = style.copy(fontSize = fontSize),
        textAlign = textAlign,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Clip,
        softWrap = false,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth && fontSize > minFontSize) {
                fontSize = (fontSize.value - stepSize.value).sp
            } else {
                readyToDraw = true
            }
        }
    )
}