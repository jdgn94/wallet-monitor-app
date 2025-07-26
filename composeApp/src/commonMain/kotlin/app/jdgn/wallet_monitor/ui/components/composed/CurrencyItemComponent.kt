package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.walletmonitor.db.v0.Currencies

@Composable
fun CurrencyItemComponent(
    currency: Currencies,
    selected: Boolean = false,
    maxWidthDp: Dp = 200.dp,
    margin: PaddingValues = PaddingValues(3.dp),
    padding: PaddingValues = PaddingValues(6.dp),
    widthFraction: Float = 0.33333334f,
    onSelect: ((Currencies) -> Unit)? = null
) {
    val currencyDeprecated = (currency.currencyTypeId == 1.toLong() && currency.countries == "[]")

    fun internalSelect() {
        onSelect?.invoke(currency)
    }

    CustomBox(
        margin = margin,
        padding = padding,
        maxWidthDp = maxWidthDp,
        widthFraction = widthFraction,
        backgroundColor = if (selected) {
            MaterialTheme.colorScheme.primary
        } else if (currencyDeprecated) {
            MaterialTheme.colorScheme.error
        } else {
            MaterialTheme.colorScheme.surface
        },
        onClick = { if (onSelect != null) internalSelect() else null },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = currency.symbolNative,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currency.code,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currency.name,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}
