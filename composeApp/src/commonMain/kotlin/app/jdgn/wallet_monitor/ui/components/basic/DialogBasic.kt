package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.jdgn.wallet_monitor.getScreenHeight
import app.jdgn.wallet_monitor.getScreenWidth
import app.jdgn.wallet_monitor.OrientationManager
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DialogBasic(
    open: Boolean,
    onDismissRequest: (() -> Unit),
    title: StringResource,
    body: @Composable (() -> Unit)
) {
    val scrollState = rememberScrollState()
    if (open) {
        BoxWithConstraints{
            val maxWidth = getScreenWidth() * 0.8f
            val maxHeight = getScreenHeight() * 0.7f

            Dialog(onDismissRequest = { onDismissRequest() }) {
                Surface(
                    modifier = Modifier
                        .width(maxWidth)
                        .height(maxHeight),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // title
                        Text(text = stringResource(title), style = MaterialTheme.typography.headlineSmall)
                        Box() {
                            FlowRow(
                                modifier = Modifier
                                    .verticalScroll(scrollState)
                                    .fillMaxWidth(),
                                itemVerticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Spacer(Modifier.fillMaxWidth().height(16.dp))
                                body()
                                Spacer(Modifier.fillMaxWidth().height(16.dp))
                            }

                            Box(
                                modifier = Modifier
                                    .height(16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                MaterialTheme.colorScheme.surface,
                                                Color.Transparent
                                            )
                                        )
                                    )
                            )

                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .height(16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                MaterialTheme.colorScheme.surface
                                            )
                                        )
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}
