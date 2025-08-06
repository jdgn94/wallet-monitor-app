package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.walletmonitor.db.v0.Banks
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.bank
import walletmonitor.composeapp.generated.resources.none

@Composable
fun BankItemComponent(
    modifier: Modifier = Modifier,
    bank: Banks? = null,
    pickWidth: Dp? = null,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(),
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color? = null,
    onClick: (() -> Unit)? = null,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp,
    contentAlignment: Alignment = Alignment.CenterStart,
    minusWidthFraction: Dp = 0.dp,
) {
    CustomBox(
        modifier = modifier,
        pickWidth = pickWidth,
        padding = padding,
        margin = margin,
        color = color,
        minusWidthFraction = minusWidthFraction,
        backgroundColor = backgroundColor,
        widthFraction = widthFraction,
        maxWidthDp = maxWidthDp,
        contentAlignment = contentAlignment,
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(Res.string.bank),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = bank?.name ?: stringResource(Res.string.none),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}