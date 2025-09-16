package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.theme.backgroundDark
import app.jdgn.wallet_monitor.theme.backgroundLight
import app.jdgn.wallet_monitor.ui.LocalResource.Icons
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CustomTextField(
    label: String = "Text",
    value: String = "",
    onChangeValue: (String) -> Unit = {},
    margin: PaddingValues = PaddingValues(),
    maxWidthDp: Dp = 10000.dp,
    widthFraction: Float = 1f,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines:  Int = if (singleLine) 1 else Int.MAX_VALUE,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    color: Color = MaterialTheme.colorScheme.primary,
    helperText: String? = null,
    errorText: String? = null,
    onClick: (() -> Unit)? = null
) {
    val errorColor = MaterialTheme.colorScheme.error
    val colors = TextFieldDefaults.colors(
        cursorColor = color,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedLabelColor = color,
        focusedContainerColor = color.copy(alpha = 0.2f),
        errorTextColor = errorColor,
        errorContainerColor = errorColor.copy(alpha = 0.2f),
    )

    Column{
        CustomBox(
            margin = margin,
            padding = PaddingValues(0.dp),
            maxWidthDp = maxWidthDp,
            widthFraction = widthFraction,
            onClick = if (!enabled) null else onClick,
            color =
                if (!errorText.isNullOrEmpty())
                    errorColor
                else
                    color
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 60.dp),
                value = value,
                onValueChange = { onChangeValue(it) },
                label = { Text(label) },
                singleLine = singleLine,
                minLines = minLines,
                maxLines = maxLines,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                enabled = enabled,
                readOnly = if (onClick != null) true else readOnly,
                textStyle = textStyle,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                prefix = prefix,
                suffix = suffix,
                colors = colors,
                isError = !errorText.isNullOrEmpty()
            )
        }
        if (!helperText.isNullOrEmpty() && errorText.isNullOrEmpty()) {
            InfoTextRow(
                text = helperText,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                icon = Icons.deleteKeyboard,
                contentDescription = "Info"
            )
        }
        if (!errorText.isNullOrEmpty()) {
            InfoTextRow(
                text = errorText,
                tint = errorColor,
                icon = Icons.plus,
                contentDescription = "Error"
            )
        }
    }
}

@Composable
private fun InfoTextRow(
    text: String,
    tint: Color,
    icon: DrawableResource,
    contentDescription: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            color = tint,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
