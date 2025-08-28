package app.jdgn.wallet_monitor.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.jdgn.wallet_monitor.ui.components.composed.MoreOptionComponent
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.about
import walletmonitor.composeapp.generated.resources.bank
import walletmonitor.composeapp.generated.resources.calendar
import walletmonitor.composeapp.generated.resources.categories
import walletmonitor.composeapp.generated.resources.expensesIncome
import walletmonitor.composeapp.generated.resources.feedback
import walletmonitor.composeapp.generated.resources.goal
import walletmonitor.composeapp.generated.resources.movements
import walletmonitor.composeapp.generated.resources.persons
import walletmonitor.composeapp.generated.resources.security
import walletmonitor.composeapp.generated.resources.settings

@Composable
fun MoreHome() {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.categories,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.bank,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.movements,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.security,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.calendar,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.goal,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.persons,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.settings,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.expensesIncome,
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.about,
        )
        MoreOptionComponent(
            icon = LocalResource.Icons.Filled.category,
            title = Res.string.feedback,
        )
    }
}