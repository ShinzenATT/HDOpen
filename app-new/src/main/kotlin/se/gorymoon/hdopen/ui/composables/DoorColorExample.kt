package se.gorymoon.hdopen.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import se.gorymoon.hdopen.dto.DoorStatus

@Composable
fun DoorColorExample(status: DoorStatus, modifier: Modifier = Modifier.padding(1.dp)){
    Surface(
        modifier = modifier,
        color = status.containerColor(),
        contentColor = status.textColor()
    ) {
        Text(status.text, Modifier.padding(5.dp), textAlign = TextAlign.Center)
    }
}