package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.close
import walletmonitor.composeapp.generated.resources.color
import walletmonitor.composeapp.generated.resources.save

@Preview
@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    initialColor: Color = Color.White,
    changeColor: (Color) -> Unit = {},
    pickWidth: Dp? = null,
    padding: PaddingValues = PaddingValues(16.dp),
    margin: PaddingValues = PaddingValues(),
    minusWidthFraction: Dp = 0.dp,
    backgroundColor: Color? = null,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp
) {
    val controller = rememberColorPickerController()
    val internalColor = remember { mutableStateOf(initialColor) }
    val showDialog = remember { mutableStateOf(false) } // dialog state

    LaunchedEffect(initialColor) {
        internalColor.value = initialColor
    }

    fun changeInternalColor(color: Color) {
        internalColor.value = color
    }

    fun openCloseDialog() {
        showDialog.value = !showDialog.value
    }
    CustomBox(
        modifier = modifier,
        color = initialColor,
        pickWidth = pickWidth,
        padding = padding,
        margin = margin,
        minusWidthFraction = minusWidthFraction,
        backgroundColor = backgroundColor,
        widthFraction = widthFraction,
        maxWidthDp = maxWidthDp,
        onClick = { openCloseDialog() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.color),
                modifier = Modifier.padding(end = 16.dp)
            )
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .size(width = 16.dp, height = 16.dp)
                    .background(initialColor)
            )
        }
    }

    DialogBasic(
        open = showDialog.value,
        onDismissRequest = { openCloseDialog() },
        title = Res.string.color,
        showActions = true,
        cancelText = Res.string.close,
        confirmText = Res.string.save,
        onConfirmRequest = { changeColor(internalColor.value) },
        content = {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .size(width = 60.dp, height = 60.dp)
                    .background(internalColor.value)
            )
            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp),
                controller = controller,
                initialColor = internalColor.value,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    changeInternalColor(colorEnvelope.color)
                }
            )
            BrightnessSlider(
                modifier = Modifier
                    .widthIn(max = 250.dp)
                    .fillMaxWidth()
                    .height(35.dp),
                controller = controller,
            )
        }
    )
}