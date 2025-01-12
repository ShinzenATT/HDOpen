package se.gorymoon.hdopen.widgets

import android.app.UiModeManager
import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState
import se.gorymoon.hdopen.utils.asProvider

class DoorWidget: GlanceAppWidget() {
    lateinit var widgetManager: AppWidgetManager

    override val sizeMode: SizeMode
        get() = SizeMode.Responsive(
            setOf(ICON, WIDE, LARGE)
        )

    companion object {
        val DARK_THEME_KEY = booleanPreferencesKey("is_dark_theme")
        private val ICON = DpSize(50.dp, 50.dp)
        private val WIDE = DpSize(100.dp, 50.dp)
        private val LARGE = DpSize(150.dp, 150.dp)
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            render(context)
        }
    }

    @Composable
    fun render(context: Context){
        val door = remember { DoorState }
        val uiService = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager

        LaunchedEffect(DoorState) {
            while (isActive) {
                refreshDoorState().join()
                Log.d("DoorWidget", "door state refreshed")
                delay(3600000)
            }
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
                            fontSize = when(LocalSize.current){
                                ICON -> 15.sp
                                WIDE -> 40.sp
                                LARGE -> 60.sp
                                else -> 80.sp
                            }
                        ),
                    )
                    if (door.timestamp != null && LocalSize.current == LARGE) {
                        val time = door.timestamp!!.toLocalDateTime(TimeZone.currentSystemDefault()).time
                        Text(
                            "$time",
                            style = TextStyle(
                                color = door.status.textColor().asProvider(),
                                fontWeight = FontWeight.Medium,
                                fontSize = when(LocalSize.current) {
                                    LARGE -> 30.sp
                                    else -> 30.sp
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}