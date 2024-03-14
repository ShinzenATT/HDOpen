package se.gorymoon.hdopen.dto

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class DoorStatus(
    val text: String,
    val containerColor: @Composable () -> Color,
    val textColor: @Composable () -> Color,
    val accentedContainerColor: @Composable () -> Color,
    val accentedTextColor: @Composable () -> Color
) {
    OPEN("Open", theme { it.tertiaryContainer }, theme { it.onTertiaryContainer }, theme { it.tertiary }, theme { it.onTertiary }),
    CLOSED("Closed", theme { it.secondaryContainer }, theme { it.onSecondaryContainer }, theme { it.secondary }, theme { it.onSecondary }),
    UNKNOWN("Unknown", theme { it.surface }, theme { it.onSurface }, theme { it.primary }, theme { it.onPrimary }),
    LOADING("Loading...", theme { it.primaryContainer }, theme { it.onPrimaryContainer }, theme { it.primary }, theme { it.onPrimary })
}

private inline fun theme(crossinline block: @Composable (ColorScheme) -> Color): @Composable () -> Color{
    return { block(MaterialTheme.colorScheme) }
}
