package app.jdgn.wallet_monitor.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Arrows
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Filled
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Calendar
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
            icon = Filled.category,
            title = Res.string.categories,
        )
        MoreOptionComponent(
            icon = Filled.bank,
            title = Res.string.bank,
        )
        MoreOptionComponent(
            icon = Filled.invoice,
            title = Res.string.movements,
        )
        MoreOptionComponent(
            icon = Filled.lock,
            title = Res.string.security,
        )
        MoreOptionComponent(
            icon = Calendar.default,
            title = Res.string.calendar,
        )
        MoreOptionComponent(
            icon = Filled.piggy,
            title = Res.string.goal,
        )
        MoreOptionComponent(
            icon = Filled.Person.default,
            title = Res.string.persons,
        )
        MoreOptionComponent(
            icon = Filled.config,
            title = Res.string.settings,
        )
        MoreOptionComponent(
            icon = Arrows.sort,
            title = Res.string.expensesIncome,
            maxWidthDp = 600.dp,
            minusWidthFraction = 24.dp,
            widthFraction = 1f
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )
        MoreOptionComponent(
            icon = Filled.info,
            title = Res.string.about,
        )
        MoreOptionComponent(
            icon = Filled.feedback,
            title = Res.string.feedback,
        )
    }
}
