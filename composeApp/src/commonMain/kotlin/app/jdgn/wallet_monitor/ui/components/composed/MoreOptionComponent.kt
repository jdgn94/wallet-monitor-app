package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoreOptionComponent(
    modifier: Modifier = Modifier,
    pickWidth: Dp? = null,
    height: Dp? = null,
    minHeight: Dp = 40.dp,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(vertical = 5.dp),
    color: Color = MaterialTheme.colorScheme.primary,
    minusWidthFraction: Dp = 16.dp,
    backgroundColor: Color? = null,
    backgroundFullColor: Boolean = false,
    splashColor: Color = color,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp,
    contentAlignment: Alignment = Alignment.CenterStart,
    icon: DrawableResource,
    title: StringResource,
    subtitle: StringResource? = null,
    onClick: (() -> Unit)? = null,
) {
    CustomBox(
        modifier = modifier,
        pickWidth = pickWidth,
        height = height,
        minHeight = minHeight,
        padding = padding,
        margin = margin,
        color = color,
        minusWidthFraction = minusWidthFraction,
        backgroundColor = backgroundColor,
        backgroundFullColor = backgroundFullColor,
        splashColor = splashColor,
        widthFraction = widthFraction,
        maxWidthDp = maxWidthDp,
        contentAlignment = contentAlignment,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(title),
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp),
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.bodyMedium,
                    color = color
                )
                if (subtitle != null)
                    Text(
                        text = stringResource(subtitle),
                        style = MaterialTheme.typography.bodySmall,
                        color = color
                    )
            }
        }
    }
}