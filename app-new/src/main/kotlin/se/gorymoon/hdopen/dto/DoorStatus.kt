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
    OPEN("Open", theme { it.primaryContainer }, theme { it.onPrimaryContainer }, theme { it.primary }, theme { it.onPrimary }),
    CRAMPED("Cramped", theme { it.secondaryContainer }, theme { it.onSecondaryContainer }, theme { it.secondary }, theme { it.onSecondary }),
    BUSY("Busy", theme { it.secondaryContainer }, theme { it.onSecondaryContainer }, theme { it.secondary }, theme { it.onSecondary }),
    CLOSED("Closed", theme { it.tertiaryContainer }, theme { it.onTertiaryContainer }, theme { it.tertiary }, theme { it.onTertiary }),
    IN_HIDING("Closed with Activity", theme { it.tertiaryContainer }, theme { it.onTertiaryContainer }, theme { it.tertiary }, theme { it.onTertiary }),
    UNKNOWN("Unknown", theme { it.surfaceVariant }, theme { it.onSurfaceVariant }, theme { it.primary }, theme { it.onPrimary })
}

private inline fun theme(crossinline block: @Composable (ColorScheme) -> Color): @Composable () -> Color{
    return { block(MaterialTheme.colorScheme) }
}
