package se.gorymoon.hdopen.ui.composables.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import se.gorymoon.hdopen.ui.models.SettingsState

@Composable
fun DoorOnboarding() {
    var settings by remember { SettingsState }
    var typedPassword by remember{ mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        Text(
            "Display of Door data",
            fontSize = 5.em,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
        )
        Text(
            "Here you can choose what kind of data you want to see on the main screen! " +
                    "You can enter a password to see additional data.",
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            textAlign = TextAlign.Justify
        )
        Spacer(Modifier.weight(1f))

        Surface(
            Modifier.fillMaxWidth()
                .padding(vertical = 5.dp)
                .clip(RoundedCornerShape(10.dp)),
            tonalElevation = BottomAppBarDefaults.ContainerElevation + 5.dp
        ) {
            Column {

                ListItem(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            settings = settings.copy(showMusic = !settings.showMusic)
                            SettingsState.saveSettings()
                       },
                    headlineContent = { Text("Show Now Playing") },
                    trailingContent = { Switch(
                        checked = settings.showMusic,
                        onCheckedChange = {value -> {
                            settings = settings.copy(showMusic = value)
                            SettingsState.saveSettings()
                        } }
                    ) }
                )

                HorizontalDivider(Modifier.fillMaxWidth().padding(vertical = 5.dp))

                ListItem(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            settings = settings.copy(showVisitors = !settings.showVisitors)
                            SettingsState.saveSettings()
                        },
                    headlineContent = { Text("Show amount of Visitors") },
                    trailingContent = { Switch(
                        checked = settings.showVisitors,
                        onCheckedChange = {value ->
                            settings = settings.copy(showVisitors = value)
                            SettingsState.saveSettings()
                        }
                    ) }
                )

                HorizontalDivider(Modifier.fillMaxWidth().padding(vertical = 5.dp))

                ListItem(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            settings = settings.copy(adsActive = !settings.adsActive)
                            SettingsState.saveSettings()
                           },
                    headlineContent = { Text("Show \"Ads\"") },
                    tonalElevation = BottomAppBarDefaults.ContainerElevation,
                    shadowElevation = 5.dp,
                    supportingContent ={ Text("Shows the next event and some info from H-sektionens Dumheter", textAlign = TextAlign.Justify) },
                    trailingContent = { Switch(
                        checked = settings.adsActive,
                        onCheckedChange = {value ->
                            settings = settings.copy(adsActive = value)
                            SettingsState.saveSettings()
                        }
                    ) }
                )
            }
        }
    }
}