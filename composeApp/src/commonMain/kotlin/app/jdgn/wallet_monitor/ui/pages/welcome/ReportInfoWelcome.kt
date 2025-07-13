package app.jdgn.wallet_monitor.ui.pages.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.jdgn.wallet_monitor.ui.LocalResource.Images
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.reportInfoWelcome
import androidx.compose.animation.core.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay

@Composable
@Preview
fun ReportInfoWelcome(isVisible: Boolean = true) {
    // State to sequence animate controller
    var animationsTriggered by remember { mutableStateOf(false) }

    // Scroll state
    val scrollState = rememberScrollState()

    // Animate values for principal image (report)
    val reportScale by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0.2f,
        animationSpec = tween(durationMillis = 800, easing = EaseOutBack)
    )

    val reportAlpha by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0f,
        animationSpec = tween(durationMillis = 800)
    )

    // Animate values for image market analysis
    val marketAnalysisScale by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = 400, easing = EaseOutBack)
    )

    val marketAnalysisRotation by animateFloatAsState(
        targetValue = if (animationsTriggered) -20f else -180f,
        animationSpec = tween(durationMillis = 800, delayMillis = 400)
    )

    // Animate values for image pie chert
    val pieChartScale by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0f,
        animationSpec = tween(durationMillis = 600, delayMillis = 600, easing = EaseOutBack)
    )

    val pieChartRotation by animateFloatAsState(
        targetValue = if (animationsTriggered) 20f else 180f,
        animationSpec = tween(durationMillis = 800, delayMillis = 600)
    )

    // Text animation
    val textOffsetY by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else 100f,
        animationSpec = tween(durationMillis = 800, delayMillis = 800, easing = EaseOutQuint)
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0f,
        animationSpec = tween(durationMillis = 800, delayMillis = 800)
    )

    // Init animation
    LaunchedEffect(isVisible) {
        animationsTriggered = false
        if (isVisible) {
            delay(100) // Delay to init animation
            animationsTriggered = true
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(vertical = 60.dp)) {
            // Principal image
            Image(
                painter = painterResource(Images.report),
                contentDescription = "Report Icon",
                modifier = Modifier
                    .padding(start = 35.dp)
                    .size(170.dp)
                    .scale(reportScale)
                    .alpha(reportAlpha)
            )

            // image market analysis
            Image(
                painter = painterResource(Images.market_analysis),
                contentDescription = "Market Analysis Icon",
                modifier = Modifier
                    .padding(start = 175.dp)
                    .size(70.dp)
                    .scale(marketAnalysisScale)
                    .rotate(marketAnalysisRotation)
            )

            // Image pie chart
            Image(
                painter = painterResource(Images.pie_chart),
                contentDescription = "Pie Chart Icon",
                modifier = Modifier
                    .padding(top = 135.dp)
                    .size(70.dp)
                    .scale(pieChartScale)
                    .rotate(pieChartRotation)
            )
        }

        Text(
            text = stringResource(Res.string.reportInfoWelcome),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .offset(y = textOffsetY.dp)
                .alpha(textAlpha),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}