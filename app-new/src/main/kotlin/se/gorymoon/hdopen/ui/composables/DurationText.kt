package se.gorymoon.hdopen.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.datetime.Clock
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun DurationText(baseDuration: Duration, fontSize: TextUnit = TextUnit.Unspecified, modifier: Modifier = Modifier, textFormat: (Duration) -> String = { it.toString() }) {
    var duration by remember { mutableStateOf(baseDuration) }

    LaunchedEffect(key1 = duration){
        while (isActive) {
            delay(1000L - Clock.System.now().toEpochMilliseconds() % 1000L)
            duration += 1.toDuration(DurationUnit.SECONDS)
        }
    }

    Text(
        text = textFormat(duration),
        fontSize = fontSize,
        modifier = modifier
    )
}