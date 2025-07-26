package app.jdgn.wallet_monitor.ui.pages.welcome

import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import app.jdgn.wallet_monitor.ui.LocalResource.Images
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walletmonitor.composeapp.generated.resources.Res
import walletmonitor.composeapp.generated.resources.bankInfoWelcome

@Composable
@Preview
fun BankInfoWelcome(isVisible: Boolean = true) {
    // Animation states
    var animationsTriggered by remember { mutableStateOf(false) }

    // Animated values for each element
    val bankOffsetX by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else -300f,
        animationSpec = tween(durationMillis = 1000, easing = EaseOutBack)
    )

    val piggyOffsetX by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else 300f,
        animationSpec = tween(durationMillis = 1000, easing = EaseOutBack)
    )

    val cardOffsetY by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else -300f,
        animationSpec = tween(durationMillis = 1000, easing = EaseOutBack)
    )

    val textOffsetY by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else 300f,
        animationSpec = tween(durationMillis = 1000, easing = EaseOutBack)
    )

    // Animations start on show component
    LaunchedEffect(isVisible) {
        animationsTriggered = false
        if (isVisible) {
            delay(100) // Delay to init animation
            animationsTriggered = true
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.padding(vertical = 60.dp)) {
            Image(
                painter = painterResource(Images.bank),
                contentDescription = "App Icon",
                modifier = Modifier
                    .padding(start = 35.dp)
                    .size(170.dp)
                    .offset(x = bankOffsetX.dp, y = 0.dp)
            )
            Image(
                painter = painterResource(Images.piggy),
                contentDescription = "App Icon",
                modifier = Modifier
                    .padding(top = 135.dp, start = 175.dp)
                    .size(70.dp)
                    .rotate(20f)
                    .offset(x = piggyOffsetX.dp, y = 0.dp)
            )
            Image(
                painter = painterResource(Images.card),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(70.dp)
                    .rotate(-20f)
                    .offset(x = 0.dp, y = cardOffsetY.dp)
            )
        }
        Text(
            text = stringResource(Res.string.bankInfoWelcome),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .offset(y = textOffsetY.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
