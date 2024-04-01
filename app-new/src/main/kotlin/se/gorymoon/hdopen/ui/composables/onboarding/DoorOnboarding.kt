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
            tonalElevation = BottomAppBarDefaults.ContainerElevation
        ) {
            Column {
                Text(
                    "Data that requires password",
                    Modifier.fillMaxWidth().padding(20.dp),
                    fontSize = 4.em,
                    fontWeight = FontWeight.Medium
                )

                ListItem(
                    modifier = Modifier.fillMaxWidth()
                        .clickable(settings.hasApiKey) {
                            settings = settings.modifyCopy(showMusic = !settings.showMusic)
                       },
                    headlineContent = { Text("Show Now Playing") },
                    trailingContent = { Switch(
                        enabled = settings.hasApiKey,
                        checked = settings.showMusic,
                        onCheckedChange = {value -> settings = settings.modifyCopy(showMusic = value) }
                    ) }
                )

                HorizontalDivider(Modifier.fillMaxWidth().padding(vertical = 5.dp))

                ListItem(
                    modifier = Modifier.fillMaxWidth()
                        .clickable(settings.hasApiKey) {
                            settings = settings.modifyCopy(showVisitors = !settings.showVisitors)
                        },
                    headlineContent = { Text("Show amount of Visitors") },
                    trailingContent = { Switch(
                        enabled = settings.hasApiKey,
                        checked = settings.showVisitors,
                        onCheckedChange = {value -> settings = settings.modifyCopy(showVisitors = value) }
                    ) }
                )

                HorizontalDivider(Modifier.fillMaxWidth().padding(vertical = 5.dp))

                if (!settings.hasApiKey) {
                    TextField(
                        typedPassword,
                        { value -> typedPassword = value },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 7.dp),
                        label = { Text("Enter Password") },
                        supportingText = { Text("Hint: Dubbel Säkerhet") },
                        placeholder = { Text("Enter password and press enter to unlock settings") },
                        singleLine = true,
                        keyboardActions = KeyboardActions {
                            settings = settings.modifyCopy(apiToken = typedPassword)
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Send
                        )
                    )
                } else {
                    Text(
                        "The API token has been saved!",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        ListItem(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { settings = settings.modifyCopy(adsActive = !settings.adsActive) },
            headlineContent = { Text("Show \"Ads\"") },
            tonalElevation = BottomAppBarDefaults.ContainerElevation,
            shadowElevation = 5.dp,
            supportingContent ={ Text("Shows the next event and some info from H-sektionens Dumheter", textAlign = TextAlign.Justify) },
            trailingContent = { Switch(
                checked = settings.adsActive,
                onCheckedChange = {value -> settings = settings.modifyCopy(adsActive = value) }
            ) }
        )
    }
}