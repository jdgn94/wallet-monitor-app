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
import walletmonitor.composeapp.generated.resources.categoryInfoWelcome
import androidx.compose.animation.core.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay

@Composable
@Preview
fun CategoryInfoWelcome(isVisible: Boolean = true) {
    // Animation states
    var animationsTriggered by remember { mutableStateOf(false) }

    // Animate values for principal image
    val bankScale by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0.3f,
        animationSpec = tween(durationMillis = 800, easing = EaseOutBack)
    )

    // Animate values for service image
    val genreOffset by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else -150f,
        animationSpec = tween(durationMillis = 600, delayMillis = 200, easing = EaseOutCubic)
    )

    val steamOffset by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else 150f,
        animationSpec = tween(durationMillis = 600, delayMillis = 300, easing = EaseOutCubic)
    )

    val netflixOffset by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else 150f,
        animationSpec = tween(durationMillis = 600, delayMillis = 400, easing = EaseOutCubic)
    )

    val spotifyOffset by animateFloatAsState(
        targetValue = if (animationsTriggered) 0f else 150f,
        animationSpec = tween(durationMillis = 600, delayMillis = 500, easing = EaseOutCubic)
    )

    // Animate values for text
    val textAlpha by animateFloatAsState(
        targetValue = if (animationsTriggered) 1f else 0f,
        animationSpec = tween(durationMillis = 800, delayMillis = 600)
    )

    // Push effect for principal image
    val pulseScale by animateFloatAsState(
        targetValue = if (animationsTriggered) 1.05f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutQuad),
            repeatMode = RepeatMode.Reverse
        )
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.padding(vertical = 60.dp)) {
            // Primary image
            Image(
                painter = painterResource(Images.category),
                contentDescription = "App Icon",
                modifier = Modifier
                    .padding(start = 35.dp, top = 20.dp)
                    .size(170.dp)
                    .scale(bankScale * if (animationsTriggered) pulseScale else 1f)
            )

            // Left image
            Image(
                painter = painterResource(Images.genre),
                contentDescription = "Genre Icon",
                modifier = Modifier
                    .padding(top = 70.dp)
                    .size(70.dp)
                    .offset(x = genreOffset.dp)
            )

            // Top right image
            Image(
                painter = painterResource(Images.steam),
                contentDescription = "Steam Icon",
                modifier = Modifier
                    .padding(start = 175.dp)
                    .size(70.dp)
                    .rotate(20f)
                    .offset(x = steamOffset.dp)
            )

            // middle right image
            Image(
                painter = painterResource(Images.netflix),
                contentDescription = "Netflix Icon",
                modifier = Modifier
                    .padding(start = 175.dp, top = 70.dp)
                    .size(70.dp)
                    .offset(x = netflixOffset.dp)
            )

            // bottom right image
            Image(
                painter = painterResource(Images.spotify),
                contentDescription = "Spotify Icon",
                modifier = Modifier
                    .padding(start = 175.dp, top = 135.dp)
                    .size(70.dp)
                    .rotate(20f)
                    .offset(x = spotifyOffset.dp)
            )
        }

        Text(
            text = stringResource(Res.string.categoryInfoWelcome),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .alpha(textAlpha),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
