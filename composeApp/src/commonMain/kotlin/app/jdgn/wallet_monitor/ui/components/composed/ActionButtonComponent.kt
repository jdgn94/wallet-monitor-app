package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Outlined
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.delete

@Composable
fun ActionButtonComponent(
    margin: PaddingValues = PaddingValues(bottom = 8.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    color: Color = MaterialTheme.colorScheme.onSurface,
    icon: Painter = painterResource(Outlined.delete),
    iconDescription: String = "Delete",
    text: String = "Text",
    onClick: () -> Unit = {}
) {
    CustomBox(
        margin = margin,
        backgroundColor = backgroundColor,
        backgroundFullColor = true,
        minusWidthFraction = 20.dp,
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = icon,
                contentDescription = iconDescription,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}