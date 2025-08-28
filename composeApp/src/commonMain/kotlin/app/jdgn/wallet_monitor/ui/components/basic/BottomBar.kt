package app.jdgn.wallet_monitor.ui.components.basic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.getScreenWidth
import app.jdgn.wallet_monitor.ui.LocalResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.accounts
import walletmonitor.composeapp.generated.resources.chart
import walletmonitor.composeapp.generated.resources.home
import walletmonitor.composeapp.generated.resources.more

sealed class Screen(val title: StringResource, val icon: DrawableResource, val activeIcon: DrawableResource) {
    object Home : Screen(
        title = Res.string.home,
        icon = LocalResource.Icons.Outlined.home,
        activeIcon = LocalResource.Icons.Filled.home
    )
    object Accounts : Screen(
        title = Res.string.accounts,
        icon = LocalResource.Icons.Outlined.cashMoneyBill,
        activeIcon = LocalResource.Icons.Filled.cashMoneyBill
    )
    object Analytics : Screen(
        title = Res.string.chart,
        icon = LocalResource.Icons.Outlined.chartMultiple,
        activeIcon = LocalResource.Icons.Filled.chartMultiple
    )
    object More : Screen(
        title = Res.string.more,
        icon = LocalResource.Icons.Outlined.more,
        activeIcon = LocalResource.Icons.Filled.more
    )
}

@Preview
@Composable
fun BottomBar(currentPage: Int, onChangePage: (page: Int) -> Unit) {
    var selectedItem by remember { mutableStateOf(currentPage) }
    val maxWidth = getScreenWidth() / 3 + 20.dp
    val items = listOf(
        Screen.Home,
        Screen.Accounts,
        Screen.Analytics,
        Screen.More
    )

    fun changePage(page: Int) {
        selectedItem = page
        onChangePage(page)
    }

    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(10.dp)
        ) {
            Row {
                items.forEachIndexed { index, item ->
                    val title = stringResource(item.title)
                    val selected = selectedItem == index
                    // animate background
                    val animatedBackgroundColor by animateColorAsState(
                        targetValue = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
                        animationSpec = tween(durationMillis = 300)
                    )
                    // animate width
                    val animatedWidth by animateDpAsState(
                        targetValue = if (selected) maxWidth else 60.dp,
                        animationSpec = tween(durationMillis = 300)
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(width = animatedWidth, height = 40.dp)
                            .background(animatedBackgroundColor)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(color = MaterialTheme.colorScheme.primary),
                                onClick = { changePage(index) }
                            )
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(
                                    resource = if (selected) item.activeIcon else item.icon
                                ),
                                contentDescription = title,
                                tint = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(24.dp)
                            )
                            AnimatedVisibility(
                                visible = selected,
                                enter = fadeIn(animationSpec = tween(durationMillis = 300))
                            ) {
                                Text(
                                    text = title,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(start = 6.dp)
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}