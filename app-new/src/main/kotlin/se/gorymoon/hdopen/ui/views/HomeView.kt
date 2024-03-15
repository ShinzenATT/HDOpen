package se.gorymoon.hdopen.ui.views

import android.view.Window
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.widget.ContentLoadingProgressBar
import coil.compose.AsyncImage
import se.gorymoon.hdopen.R
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.ui.composables.AdBar
import se.gorymoon.hdopen.ui.composables.AppBar
import se.gorymoon.hdopen.ui.composables.DoorDisplay
import se.gorymoon.hdopen.ui.models.AdState
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState

@Composable
fun HomeView(window: Window? = null) {
    val  state by  remember { DoorState }
    val (status, _, isLoading) = state

    HDOpenTheme{
        Scaffold(
            topBar = {
                AppBar(
                    status.accentedContainerColor(),
                    status.accentedTextColor(),
                    window
                )
            },
            bottomBar ={ AdBar() },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { refreshDoorState() },
                    containerColor = status.accentedContainerColor(),
                    contentColor = status.accentedTextColor()
                ) {
                    if(isLoading) {
                        CircularProgressIndicator(
                            color = status.accentedTextColor()
                        )
                    } else {
                        Icon(Icons.Filled.Refresh, "Refresh")
                    }
                }
            }
        ) {
            DoorDisplay(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    HomeView()
}