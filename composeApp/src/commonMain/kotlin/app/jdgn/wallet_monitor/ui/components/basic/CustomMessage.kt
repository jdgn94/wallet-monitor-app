package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.theme.extraColor
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class MessageType {
    INFO,
    SUCCESS,
    WARNING,
    ERROR
}

@Preview
@Composable
fun CustomMessage(
    title: String,
    message: String,
    type: MessageType = MessageType.INFO,
    icon: Painter? = null
) {
    val color = when (type) {
        MessageType.INFO -> MaterialTheme.colorScheme.extraColor.info.color
        MessageType.SUCCESS -> MaterialTheme.colorScheme.extraColor.success.color
        MessageType.WARNING -> MaterialTheme.colorScheme.extraColor.warning.color
        MessageType.ERROR -> MaterialTheme.colorScheme.error
    }
    CustomBox(
        widthFraction = 1f,
        backgroundColor = color.copy(alpha = 0.1f),
        color = color
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    Icon(
                        painter = icon,
                        tint = color,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp).size(24.dp)
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = color,
                textAlign = TextAlign.Center
            )
        }
    }
}
