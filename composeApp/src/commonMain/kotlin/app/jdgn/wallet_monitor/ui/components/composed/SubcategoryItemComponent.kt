package app.jdgn.wallet_monitor.ui.components.composed

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.LocalResource
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox
import app.walletmonitor.db.v0.Subcategories
import app.jdgn.wallet_monitor.utils.hexStringToColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun SubcategoryItemComponent(
    subcategory: Subcategories,
    modifier: Modifier = Modifier,
    pickWidth: Dp? = null,
    height: Dp? = null,
    minHeight: Dp = 60.dp,
    margin: PaddingValues = PaddingValues(),
    minusWidthFraction: Dp = 0.dp,
    widthFraction: Float = 0.5f,
    maxWidthDp: Dp = 300.dp,
    onClick: (() -> Unit)? = null
) {
    val color = hexStringToColor(subcategory.color)
    CustomBox(
        modifier = modifier,
        pickWidth = pickWidth,
        height = height,
        minHeight = minHeight,
        padding = PaddingValues(0.dp),
        margin = margin,
        color = color,
        minusWidthFraction = minusWidthFraction,
        splashColor = color,
        widthFraction = widthFraction,
        maxWidthDp = maxWidthDp,
        contentAlignment = Alignment.CenterStart,
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(60.dp)
                .rotate(20f)
                .alpha(0.35f),
            painter = painterResource(LocalResource.Icons.Outlined.bank),
            contentDescription = subcategory.name,
            tint = color,
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = subcategory.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
