package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.jdgn.wallet_monitor.getScreenHeight
import app.jdgn.wallet_monitor.getScreenWidth
import app.jdgn.wallet_monitor.theme.extraColor
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(FlowPreview::class)
@Composable
fun DialogBasic(
    open: Boolean,
    onDismissRequest: (() -> Unit),
    searchAction: ((String) -> Unit)? = null,
    title: StringResource,
    body: @Composable (() -> Unit)
) {
    val scrollState = rememberScrollState()
    val maxWidth = getScreenWidth() * 0.8f
    val maxHeight = getScreenHeight() * 0.7f

    if (open) {
        BoxWithConstraints{
            var searchText by remember { mutableStateOf("") }
            var searchActive by remember { mutableStateOf(false) }
            val focusRequester = remember { FocusRequester() }
            val searchTextFlow = remember { MutableStateFlow(searchText) }

            fun changeSearchState() {
                searchActive = !searchActive
            }

            fun setSearchText(text:String) {
                searchText = text
            }

            // effect to update flow on change text
            LaunchedEffect(searchText) {
                searchTextFlow.value = searchText
            }

            // effect to get flow with debounce
            LaunchedEffect(searchTextFlow) {
                searchTextFlow
                    .debounce(1000L) // 1000ms = 1 segundo
                    .collect { debouncedText ->
                        // only execute if text is not empty
                        searchAction?.invoke(debouncedText)
                    }
            }

            Dialog(onDismissRequest = { onDismissRequest() }) {
                Surface(
                    modifier = Modifier
                        .widthIn(max = maxWidth)
                        .heightIn(max = maxHeight),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // dialog title
                        Row(
                            modifier = Modifier.padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (searchActive) {
                                // show input to search
                                BasicTextField(
                                    value = searchText,
                                    onValueChange = { setSearchText(it) },
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .weight(1f)
                                        .focusRequester(focusRequester)
                                )

                                LaunchedEffect(Unit) {
                                    focusRequester.requestFocus()
                                }

                                TextButton(onClick = { changeSearchState() }) {
                                    Text(text = "Cancel")
                                }
                            } else {
                                // show title input
                                Text(
                                    text = stringResource(title),
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.weight(1f)
                                )
                                if (searchAction != null) {
                                    TextButton(onClick = { changeSearchState() }) {
                                        Text(text = "Search")
                                    }
                                }
                            }
                        }
                        // dialog body
                        Box(modifier = Modifier.heightIn(max = maxHeight - 150.dp)) {
                            FlowRow(
                                modifier = Modifier
                                    .verticalScroll(scrollState),
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
                        // dialog actions
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = { onDismissRequest() }) {
                                Text(
                                    text = "OK",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.extraColor.info.color
                                )
                            }
                            TextButton(onClick = { onDismissRequest() }) {
                                Text(
                                    text = "Cancel",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
