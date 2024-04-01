package se.gorymoon.hdopen.ui.viewmodels

import android.content.res.Configuration
import android.view.View
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import se.gorymoon.hdopen.ui.theme.theme

typealias SystemColorSetter = @Composable (Color, Color?) -> Unit

fun createSystemColorSetter(window: Window, view: View): SystemColorSetter {
    val insetsController = WindowCompat.getInsetsController(window, view)
    return {statusColor, navColor ->
        window.statusBarColor = statusColor.toArgb()
        window.navigationBarColor = navColor?.toArgb() ?: statusColor.toArgb()
        val uiMode = window.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if(statusColor == theme().surface || statusColor == theme().background){
            insetsController.isAppearanceLightStatusBars = uiMode != Configuration.UI_MODE_NIGHT_YES
        } else{
            insetsController.isAppearanceLightStatusBars = uiMode == Configuration.UI_MODE_NIGHT_YES
        }
    }
}