package se.gorymoon.hdopen.dto

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class DoorStatus(
    val text: String,
    val containerColor: @Composable () -> Color,
    val textColor: @Composable () -> Color
) {
    OPEN("Open", theme { it.tertiaryContainer }, theme { it.onTertiaryContainer }),
    CLOSED("Closed", theme { it.secondaryContainer }, theme { it.onSecondaryContainer }),
    UNKNOWN("Unknown", theme { it.primaryContainer }, theme { it.onPrimaryContainer }),
    LOADING("Loading...", theme { it.primaryContainer }, theme { it.onPrimaryContainer })
}

private inline fun theme(crossinline block: @Composable (ColorScheme) -> Color): @Composable () -> Color{
    return { block(MaterialTheme.colorScheme) }
}
