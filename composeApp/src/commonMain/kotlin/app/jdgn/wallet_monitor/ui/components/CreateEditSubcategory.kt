package app.jdgn.wallet_monitor.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import app.walletmonitor.db.v0.Subcategories

@Composable
fun CreateEditSubcategory(
    subcategory: Subcategories? = null,
    color: Color? = null,
    onCreated: (Subcategories) -> Unit,
) {
    val name = remember { mutableStateOf(subcategory?.name ?: "") }
    val icon = remember { mutableStateOf(subcategory?.icon ?: "") }
    val color = remember { mutableStateOf(subcategory?.color ?: color?: "") }
}
