package app.jdgn.wallet_monitor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.jdgn.wallet_monitor.ui.components.basic.CustomBox

@Preview
@Composable
fun SelectCurrencyComponent() {
    fun test() {
        println("hellow from selectCurrencyComponent")
    }

    CustomBox(
        margin = PaddingValues(10.dp),
        onClick = { test() }
    ) {
        Text("Hola vale")
    }
}