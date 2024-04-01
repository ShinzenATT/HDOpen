package se.gorymoon.hdopen.ui.composables.onboarding

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import se.gorymoon.hdopen.dto.DoorStatus.*
import se.gorymoon.hdopen.ui.composables.DoorColorExample
import se.gorymoon.hdopen.ui.models.SettingsState
import se.gorymoon.hdopen.ui.theme.theme

@Composable
fun ThemeOnboarding(){
    var state by remember { SettingsState }
    val context = LocalContext.current
    val exampleModifier = Modifier.fillMaxWidth()
        .padding(vertical = 5.dp)
        .clip(RoundedCornerShape(10.dp))

    Column(Modifier.fillMaxSize()) {
        Text(
            "Your Material Theme",
            fontSize = 5.em,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
        )
        Text(
            "If your phone is running Android 12 or above then you can use your own theme in your apps. Below you can see how your theme looks in the various states!",
            modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp)
        )

        Text(
            "Door examples:",
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 5.dp),
            fontWeight = FontWeight.Medium
        )
        DoorColorExample(OPEN, exampleModifier)
        DoorColorExample(CLOSED, exampleModifier)
        DoorColorExample(UNKNOWN, exampleModifier)
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                val intent = Intent(Settings.ACTION_SETTINGS)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = theme().surfaceColorAtElevation(BottomAppBarDefaults.ContainerElevation)
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
            ) { Text("Open device settings") }
        ListItem(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,) { state = state.modifyCopy(!state.useDeviceTheme) },
            headlineContent = { Text("Use device theme") },
            tonalElevation = BottomAppBarDefaults.ContainerElevation,
            shadowElevation = 5.dp,
            supportingContent = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {{
                Text("Your device is running a version below Android 12")
            }} else null,
            trailingContent = { Switch(
                enabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
                checked = state.useDeviceTheme,
                onCheckedChange = {value -> state = state.modifyCopy(value) }
            ) }
        )
    }
}