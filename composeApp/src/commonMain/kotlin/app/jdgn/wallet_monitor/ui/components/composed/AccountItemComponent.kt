package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource.Images
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.utils.hexStringToColor
import app.walletmonitor.db.v0.GetAll
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.transactions

@Composable
fun AccountItemComponent(
    account: GetAll,
    onClick: (Long) -> Unit,
    margin: PaddingValues = PaddingValues(end = 10.dp)
) {
    val color = hexStringToColor(account.color)
    val transactions = stringResource(Res.string.transactions)
    val image = if (account.bankId != null)
            Images.bank
        else
            Images.cash

    CustomBox(
        pickWidth = 150.dp,
        color = color,
        margin = margin,
        padding = PaddingValues(0.dp),
        onClick = { onClick(account.id) }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(
                    resource = image
                ),
                contentDescription = "cash",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(60.dp)
                    .rotate(20f)
                    .alpha(0.35f)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height(12.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(color)
                    )
                    Text(
                        text = account.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = "${account.symbolNative} ${account.amount}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "152 $transactions",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
