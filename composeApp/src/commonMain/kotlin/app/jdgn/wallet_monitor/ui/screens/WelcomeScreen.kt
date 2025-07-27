package app.jdgn.wallet_monitor.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.jdgn.wallet_monitor.ui.pages.welcome.BankInfoWelcome
import app.jdgn.wallet_monitor.ui.pages.welcome.ReportInfoWelcome
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource.Icons.Arrows
import app.jdgn.wallet_monitor.ui.pages.welcome.CategoryInfoWelcome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun WelcomeScreen(navController: NavHostController) {
    // Coroutine scope
    val scope = rememberCoroutineScope()
    // Actual page control
    val pagerState = rememberPagerState(initialPage = 0) { 3 } // 3 page in total
    // Track if user has visited the last page
    val hasVisitedLastPage = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    // Update the state when user visits the last page
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 2) {
            hasVisitedLastPage.value = true
        }
    }
    // FAB animation values
    val fabScale by animateFloatAsState(
        targetValue = if (hasVisitedLastPage.value) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val fabAlpha by animateFloatAsState(
        targetValue = if (hasVisitedLastPage.value) 1f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    val fabRotation by animateFloatAsState(
        targetValue = if (hasVisitedLastPage.value) 0f else 180f,
        animationSpec = tween(durationMillis = 500)
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navigate to your main app screen
                    navController.navigate("initial_config")
                },
                modifier = Modifier
                    .scale(fabScale)
                    .alpha(fabAlpha)
                    .rotate(fabRotation)
                    .padding(bottom = 16.dp, end = 16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(Arrows.forward),
                    contentDescription = "Continue",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { ScreenContain(it, scope, pagerState, scrollState) }
}

@Composable
private fun ScreenContain(internalPadding: PaddingValues, scope: CoroutineScope, pagerState: PagerState, scrollState: ScrollState){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // horizontal paginate
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.verticalScroll(scrollState).weight(1f)
        ) { page ->
            // Show page
            when (page) {
                0 ->
                    if (pagerState.currentPage == 0)
                        BankInfoWelcome(isVisible = pagerState.currentPage == 0)
                1 ->
                    if (pagerState.currentPage == 1)
                        ReportInfoWelcome(isVisible = pagerState.currentPage == 1)
                2 ->
                    if (pagerState.currentPage == 2)
                        CategoryInfoWelcome(isVisible = pagerState.currentPage == 2)
            }
        }

        // Page indicator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(10.dp)
                        .background(color, CircleShape)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(iteration)
                            }
                        }
                )
            }
        }
    }
}
