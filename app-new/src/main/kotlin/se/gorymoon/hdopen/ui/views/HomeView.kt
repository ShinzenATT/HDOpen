package se.gorymoon.hdopen.ui.views

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import se.gorymoon.hdopen.ui.composables.AdBar
import se.gorymoon.hdopen.ui.composables.AppBar
import se.gorymoon.hdopen.ui.composables.DoorDisplay
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.ui.models.SettingsState
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState

@Composable
fun HomeView(nav: NavController) {
    Log.d("Home View", "Recomposed view")
    val  state by  remember { DoorState }
    val settings by remember { SettingsState }
    val (status, _, _, isLoading) = state

    LaunchedEffect(Unit){
        refreshDoorState().join()
    }

    Scaffold(
        topBar = {
            AppBar(
                status.accentedContainerColor(),
                status.accentedTextColor(),
                nav
            )
        },
        bottomBar ={ if(settings.adsActive) AdBar() },
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
        DoorDisplay(it, state, settings)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    HomeView(rememberNavController())
}