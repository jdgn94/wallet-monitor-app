package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.utils.categoryCelebrations
import app.jdgn.wallet_monitor.utils.categoryEmergency
import app.jdgn.wallet_monitor.utils.categoryEntertainment
import app.jdgn.wallet_monitor.utils.categoryExtras
import app.jdgn.wallet_monitor.utils.categoryFood
import app.jdgn.wallet_monitor.utils.categorySchool
import app.jdgn.wallet_monitor.utils.categoryService
import app.jdgn.wallet_monitor.utils.categoryShopping
import app.jdgn.wallet_monitor.utils.categoryTechnology
import app.jdgn.wallet_monitor.utils.categoryTravel
import app.jdgn.wallet_monitor.utils.imageByString
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.qualifier.named
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.celebration
import walletmonitor.composeapp.generated.resources.education
import walletmonitor.composeapp.generated.resources.entertainment
import walletmonitor.composeapp.generated.resources.food
import walletmonitor.composeapp.generated.resources.health
import walletmonitor.composeapp.generated.resources.icon
import walletmonitor.composeapp.generated.resources.other
import walletmonitor.composeapp.generated.resources.selectIcon
import walletmonitor.composeapp.generated.resources.service
import walletmonitor.composeapp.generated.resources.shopping
import walletmonitor.composeapp.generated.resources.technology
import walletmonitor.composeapp.generated.resources.travel

@Preview
@Composable
fun IconSelector(
    defaultSelected: String? = null,
    onChangeValue: (String) -> Unit,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val open = remember { mutableStateOf(false) }
    val iconSelected = remember { mutableStateOf("") }

    LaunchedEffect(open) {
        if (iconSelected.value.isEmpty()) {
            iconSelected.value = defaultSelected ?: ""
        }
    }

    fun selectIcon(name: String) {
        iconSelected.value = name
        open.value = false
        onChangeValue(name)
    }

    CustomBox(
        maxWidthDp = 60.dp,
        height = 60.dp,
        color = color,
        onClick = { open.value = true }
    ) {
        Column {
            if (iconSelected.value == "")
                Text(
                    text = stringResource(Res.string.icon),
                    style = MaterialTheme.typography.bodySmall
                )
            else
                Image(
                    painter = painterResource(imageByString(iconSelected.value)),
                    contentDescription = iconSelected.value,
                    modifier = Modifier
                        .size(50.dp)
                )
        }
    }

    DialogBasic(
        open = open.value,
        onDismissRequest = { open.value = false },
        title = Res.string.selectIcon
    ) {
        CategoryTitle(Res.string.celebration)
        categoryCelebrations.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.health)
        categoryEmergency.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.entertainment)
        categoryEntertainment.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.food)
        categoryFood.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.shopping)
        categoryShopping.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.service)
        categoryService.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.technology)
        categoryTechnology.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.travel)
        categoryTravel.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.education)
        categorySchool.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
        CategoryTitle(Res.string.other)
        categoryExtras.forEach { name ->
            ImageComponent(
                name = name,
                selected = iconSelected.value,
                onSelect = { selectIcon(name) })
        }
    }
}

@Composable
private fun CategoryTitle(name: StringResource) {
    Text(
        text = stringResource(name),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)
    )
}

@Composable
private fun ImageComponent(name: String, selected: String, onSelect: () -> Unit) {
    CustomBox(
        backgroundColor =
            if (name == selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface,
        maxWidthDp = 60.dp,
        height = 60.dp,
        margin = PaddingValues(3.dp),
        onClick = { onSelect() }
    ) {
        Image(
            painter = painterResource(imageByString(name)),
            contentDescription = name,
            modifier = Modifier
                .size(50.dp)
        )
    }
}
