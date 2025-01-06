package se.gorymoon.hdopen.widgets

import android.app.UiModeManager
import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState
import se.gorymoon.hdopen.utils.asProvider
import se.gorymoon.hdopen.utils.minSize

class DoorWidget: GlanceAppWidget() {
    lateinit var widgetManager: AppWidgetManager

    override val sizeMode: SizeMode
        get() = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            render(context)
        }
    }

    @Composable
    fun render(context: Context){
        val door = remember { DoorState }
        val size = LocalSize.current
        val uiService = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager

        LaunchedEffect(Unit) {
            refreshDoorState()
        }
        HDOpenTheme(uiService.nightMode == UiModeManager.MODE_NIGHT_YES, true, context) {
            Column(
                modifier = GlanceModifier.fillMaxSize().padding(16.dp).background(door.status.containerColor())
                    .clickable { refreshDoorState() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (door.isLoading)
                    CircularProgressIndicator(color = door.status.textColor().asProvider())
                else {
                    Text(
                        door.status.text,
                        style = TextStyle(
                            color = door.status.textColor().asProvider(),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = (size.minSize.value / 4).sp
                        ),
                    )
                    if (size.height.value > 100) {
                        Text(
                            "~${door.duration}",
                            style = TextStyle(
                                color = door.status.textColor().asProvider(),
                                fontWeight = FontWeight.Medium,
                                fontSize = (size.minSize.value / 10).sp
                            )
                        )
                    }
                }
            }
        }
    }

    companion object {
        val DARK_THEME_KEY = booleanPreferencesKey("is_dark_theme")
    }
}