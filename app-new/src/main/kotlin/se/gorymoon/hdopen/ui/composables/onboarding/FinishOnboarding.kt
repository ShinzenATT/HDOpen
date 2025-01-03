package se.gorymoon.hdopen.ui.composables.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import se.gorymoon.hdopen.navigation.Route
import se.gorymoon.hdopen.navigation.popStackAndNavigate
import se.gorymoon.hdopen.ui.models.SettingsState

@Composable
fun FinishOnboarding(nav: NavController){
    Column {
        Text(
            "That's All!",
            fontSize = 5.em,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
        )
        Text(
            "The app is now ready for use! You can also consider installing the Wear OS app as well.",
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 20.dp),
            textAlign = TextAlign.Justify
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {},
            colors = ButtonDefaults.filledTonalButtonColors(),
            modifier = Modifier.fillMaxWidth().padding(vertical = 7.dp)
        ) {
            Text("Get Wear OS App")
        }

        Button(
            onClick = {
                SettingsState.value = SettingsState.value.copy(onboardingCompleted = true)
                SettingsState.saveSettings()
                nav.popStackAndNavigate(Route.Door)
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 7.dp)
        ){
            Text("Start using the app!")
        }
    }
}