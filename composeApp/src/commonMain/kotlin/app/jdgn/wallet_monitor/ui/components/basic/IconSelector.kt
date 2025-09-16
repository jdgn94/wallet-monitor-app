package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.icon

@Composable
fun IconSelector(
    // value: String,
    // onChangeValue: (String) -> Unit
    color: Color = MaterialTheme.colorScheme.primary
) {
    CustomBox(
        maxWidthDp = 60.dp,
        height = 60.dp,
        color = color
    ) {
        Column {
            Text(
                text = stringResource(Res.string.icon),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
