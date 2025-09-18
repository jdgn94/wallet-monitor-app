package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.theme.darkProfitGreenColor
import app.jdgn.wallet_monitor.theme.extraColor
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Outlined
import app.jdgn.wallet_monitor.ui.components.basic.ButtonAddItem
import app.jdgn.wallet_monitor.ui.components.basic.ColorPicker
import app.jdgn.wallet_monitor.ui.components.basic.CustomColumn
import app.jdgn.wallet_monitor.ui.components.basic.CustomTextField
import app.jdgn.wallet_monitor.ui.components.basic.IconSelector
import app.jdgn.wallet_monitor.ui.components.basic.TopBar
import app.jdgn.wallet_monitor.ui.components.composed.ActionButtonComponent
import app.jdgn.wallet_monitor.utils.generateRandomColorHex
import app.jdgn.wallet_monitor.utils.hexStringToColor
import app.wallet_monitor.shared.viewModel.CategoryViewModel
import app.wallet_monitor.shared.viewModel.SubcategoryViewModel
import app.walletmonitor.db.v0.Subcategories
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.cancel
import walletmonitor.composeapp.generated.resources.category
import walletmonitor.composeapp.generated.resources.delete
import walletmonitor.composeapp.generated.resources.description
import walletmonitor.composeapp.generated.resources.name
import walletmonitor.composeapp.generated.resources.save
import walletmonitor.composeapp.generated.resources.subcategories

@Composable
fun CategoryScreen(navController: NavHostController, id: Long? = null) {
    val viewModelCategory = koinViewModel<CategoryViewModel>()
    val viewModelSubcategory = koinViewModel<SubcategoryViewModel>()
    val categoryId = remember { mutableStateOf(id ?: 0L) }
    val icon = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val color = remember { mutableStateOf(generateRandomColorHex()) }
    val subcategories = remember { mutableStateOf<List<Subcategories>>(listOf()) }

    fun changeName(value: String) {
        name.value = value
    }

    fun changeDescription(value: String) {
        description.value = value
    }

    fun openSubcategory(subcategory: Subcategories? = null) {

    }

    fun save() {}

    fun delete() {}

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                backButton = true,
                title = stringResource(Res.string.category),
                color = color.value
            )
        }
    ) { padding ->
        CustomColumn(
            modifier = Modifier.padding(padding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                IconSelector(
                    color = color.value
                )
                Box(modifier = Modifier.width(16.dp))
                CustomTextField(
                    label = stringResource(Res.string.name),
                    value = name.value,
                    onChangeValue = { changeName(it) },
                    color = color.value,
                )
            }
            CustomTextField(
                label = stringResource(Res.string.description),
                value = description.value,
                onChangeValue = { changeDescription(it) },
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                color = color.value,
                margin = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            )
            ColorPicker(
                margin = PaddingValues(horizontal = 16.dp),
                initialColor = color.value,
                maxWidthDp = 600.dp,
                widthFraction = 1f,
                changeColor = { color.value = it },
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(Res.string.subcategories),
                style = MaterialTheme.typography.titleLarge,
            )
            HorizontalDivider(color = color.value, modifier = Modifier.padding(horizontal = 16.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                ButtonAddItem(
                    color = color.value,
                    onClick = { openSubcategory() }
                ){}
            }

            FlowRow(
                modifier = Modifier.padding(horizontal = 16.dp ).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.Center
            ) {
                ActionButtonComponent(
                    backgroundColor = MaterialTheme.colorScheme.extraColor.success.color,
                    color = MaterialTheme.colorScheme.extraColor.success.onColor,
                    shadowColor = color.value,
                    icon = painterResource(Outlined.save),
                    iconDescription = "Save",
                    text = stringResource(Res.string.save),
                    onClick = { save() }
                )
                ActionButtonComponent(
                    color = MaterialTheme.colorScheme.error,
                    icon = painterResource(Outlined.cancel),
                    shadowColor = color.value,
                    iconDescription = "Cancel",
                    text = stringResource(Res.string.cancel),
                    onClick = { navController.popBackStack() }
                )
                ActionButtonComponent(
                    backgroundColor = MaterialTheme.colorScheme.error,
                    color = MaterialTheme.colorScheme.onError,
                    shadowColor = color.value,
                    icon = painterResource(Outlined.delete),
                    iconDescription = "Delete",
                    text = stringResource(Res.string.delete),
                    onClick = { delete() }
                )
            }
        }
    }
}
