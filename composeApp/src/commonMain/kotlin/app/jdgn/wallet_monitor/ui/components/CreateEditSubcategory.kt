package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.ColorPicker
import app.jdgn.wallet_monitor.ui.components.basic.CustomTextField
import app.jdgn.wallet_monitor.ui.components.basic.DialogBasic
import app.jdgn.wallet_monitor.ui.components.basic.IconSelector
import app.jdgn.wallet_monitor.utils.colorToHex
import app.jdgn.wallet_monitor.utils.hexStringToColor
import app.walletmonitor.db.v0.Subcategories
import org.jetbrains.compose.resources.stringResource
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.cancel
import walletmonitor.composeapp.generated.resources.name
import walletmonitor.composeapp.generated.resources.save
import walletmonitor.composeapp.generated.resources.subcategory
import kotlin.random.Random

@Composable
fun CreateEditSubcategory(
    open: Boolean,
    subcategory: Subcategories? = null,
    color: Color,
    onDismissRequest: () -> Unit,
    onSuccess: (Subcategories) -> Unit,
) {
    val name = remember { mutableStateOf("") }
    val icon = remember { mutableStateOf("") }
    val localColor = remember { mutableStateOf(Color.Red)}

    LaunchedEffect(open) {
        name.value = subcategory?.name ?: ""
        icon.value = subcategory?.icon ?: ""
        localColor.value = if(subcategory != null) hexStringToColor(subcategory.color)
        else color
    }

    fun create() {
        val subcategoryTemp =  Subcategories(
            id = subcategory?.id ?: (Random.nextLong() * -1),
            name = name.value,
            icon = icon.value,
            categoryId = subcategory?.categoryId ?: 0L,
            color = colorToHex(localColor.value),
            createdAt = subcategory?.createdAt ?: "",
            updatedAt = subcategory?.updatedAt ?: "",
        )

        onSuccess(subcategoryTemp)
        onDismissRequest()
    }

    DialogBasic(
        open = open,
        onDismissRequest = onDismissRequest,
        confirmText = Res.string.save,
        cancelText = Res.string.cancel,
        onConfirmRequest = { create() },
        title = Res.string.subcategory,
    ) {
        Row(modifier = Modifier.widthIn(max = 400.dp).padding(bottom = 8.dp)) {
            IconSelector(color = localColor.value)
            CustomTextField(
                margin = PaddingValues(start = 8.dp),
                value = name.value,
                onChangeValue = { name.value = it },
                label = stringResource(Res.string.name),
                color = localColor.value
            )
        }
        ColorPicker(
            initialColor = localColor.value,
            changeColor = { localColor.value = it },
            widthFraction = 1f,
            maxWidthDp = 400.dp
        )
    }
}
