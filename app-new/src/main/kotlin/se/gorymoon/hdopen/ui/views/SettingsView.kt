package se.gorymoon.hdopen.ui.views

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.ui.composables.DoorColorExample
import se.gorymoon.hdopen.ui.models.SettingsState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.theme.theme
import se.gorymoon.hdopen.ui.viewmodels.SystemColorSetter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(nav: NavController, setSystemColor: SystemColorSetter){
    var state by remember { SettingsState }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    setSystemColor(theme().surface, null)

    Scaffold(
        containerColor = theme().surface,
        contentColor = theme().onSurface,
        topBar = {
            LargeTopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { nav.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = theme().surface,
                    titleContentColor = theme().onSurface,
                    navigationIconContentColor = theme().onSurface
                )
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).verticalScroll(scrollState)) {
            ListItem(
                modifier = Modifier.clickable(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    state = state.copy(!state.useDeviceTheme)
                    SettingsState.saveSettings()
                },
                headlineContent = { Text("Use device theme") },
                supportingContent = {
                    Row {
                        DoorColorExample(DoorStatus.BUSY)
                        DoorColorExample(DoorStatus.OPEN)
                        DoorColorExample(DoorStatus.CLOSED)
                        DoorColorExample(DoorStatus.UNKNOWN)
                    }
                },
                trailingContent = {
                    Switch(
                        enabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
                        checked = state.useDeviceTheme,
                        onCheckedChange = { value ->
                            state = state.copy(value)
                            SettingsState.saveSettings()
                        }
                    )
                }
            )

            HorizontalDivider(Modifier.padding(vertical = 10.dp))

            ListItem(
                modifier = Modifier.clickable {
                    state = state.copy(adsActive = !state.adsActive)
                    SettingsState.saveSettings()
                },
                headlineContent = { Text("Show \"Ads\"") },
                trailingContent = {
                    Switch(
                        checked = state.adsActive,
                        onCheckedChange = { value ->
                            state = state.copy(adsActive = value)
                            SettingsState.saveSettings()
                        }
                    )
                }
            )

            HorizontalDivider(Modifier.padding(vertical = 10.dp))

            ListItem(
                modifier = Modifier.clickable {
                    state = state.copy(showMusic = !state.showMusic)
                    SettingsState.saveSettings()
                },
                headlineContent = { Text("Show Now Playing") },
                trailingContent = {
                    Switch(
                        checked = state.showMusic,
                        onCheckedChange = { value ->
                            state = state.copy(showMusic = value)
                            SettingsState.saveSettings()
                        }
                    )
                }
            )

            HorizontalDivider(Modifier.padding(vertical = 10.dp))

            ListItem(
                modifier = Modifier.clickable {
                    state = state.copy(showVisitors = !state.showVisitors)
                    SettingsState.saveSettings()
                },
                headlineContent = { Text("Show Amount of Visitors") },
                trailingContent = {
                    Switch(
                        checked = state.showVisitors,
                        onCheckedChange = { value ->
                            state = state.copy(showVisitors = value)
                            SettingsState.saveSettings()
                        }
                    )
                }
            )

            HorizontalDivider(Modifier.padding(vertical = 10.dp))

            ListItem(
                modifier = Modifier.clickable {
                      val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                    context.startActivity(intent)
                },
                headlineContent = { Text("Open Notification Settings") },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.OpenInNew, "Open settings")
                }
            )
        }
    }
}

@Composable
@Preview
private fun SettingsPreview(){
    HDOpenTheme {
        SettingsView(rememberNavController()) {_,_ -> }
    }
}