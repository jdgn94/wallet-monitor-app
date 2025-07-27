package app.jdgn.wallet_monitor.ui.pages.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.ButtonAddItem
import app.jdgn.wallet_monitor.ui.components.basic.CustomRow
import app.jdgn.wallet_monitor.ui.components.composed.AccountItemComponent
import app.wallet_monitor.shared.viewModel.AccountViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountsHome() {
    val scrollState = rememberScrollState()
    val viewModel = koinViewModel<AccountViewModel>()
    val allAccounts = viewModel.getAccounts()

    fun accountDetails(id: Long) {
        println("go to account details $id")
    }

    CustomRow(
    ) {
        allAccounts.forEach { account ->
            AccountItemComponent(account, onClick = { accountDetails(it) })
        }
        ButtonAddItem(
            onClick = { println("add account") },
        )
    }
}