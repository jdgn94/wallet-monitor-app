package app.jdgn.wallet_monitor.ui.pages.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.jdgn.wallet_monitor.ui.components.SelectCurrencyComponent
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.mainCurrency

@Preview
@Composable
fun SetDefaultCurrency() {
    Column {
        Text(
            text = stringResource(Res.string.mainCurrency),
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        SelectCurrencyComponent(defaultCurrencySelectId = 149.toLong())
    }
}
