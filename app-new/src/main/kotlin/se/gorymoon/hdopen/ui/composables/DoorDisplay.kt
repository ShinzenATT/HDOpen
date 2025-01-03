package se.gorymoon.hdopen.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.dto.UserSettings
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState
import se.gorymoon.hdopen.utils.asData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun DoorDisplay(padding: PaddingValues, state: DoorData, settings: UserSettings){
    val (status, duration, isLoading) = state
    val displayText = if(isLoading) "Loading..." else status.text

    // A surface container using the 'background' color from the theme
    Surface(
        color = status.containerColor(),
        modifier = Modifier.fillMaxSize().padding(padding).clickable { refreshDoorState() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = displayText,
                color = status.textColor(),
                fontSize = 15.em,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 1.2.em,
            )
            if (isLoading){
                LinearProgressIndicator(color = status.textColor())
            } else if(duration != null){
                DurationText(duration, 5.em, modifier = Modifier.padding(bottom = 10.dp)) { "$it ago" }

                if(settings.showMusic)
                    NowPlayingCard(Modifier.padding(15.dp), containerColor = status.accentedContainerColor(), textColor = status.accentedTextColor())
                if(settings.showVisitors)
                    PresenceCard(Modifier.padding(15.dp), containerColor = status.accentedContainerColor(), textColor = status.accentedTextColor())
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun OpenPreview(){
    HDOpenTheme {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.OPEN.asData(69.toDuration(DurationUnit.MINUTES)),
            UserSettings()
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun ClosedPreview(){
    HDOpenTheme {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.CLOSED.asData(69.toDuration(DurationUnit.MINUTES)),
            UserSettings()
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun UnknownPreview(){
    HDOpenTheme {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.UNKNOWN.asData(69.toDuration(DurationUnit.MINUTES)),
            UserSettings()
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun BusyPreview(){
    HDOpenTheme {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.BUSY.asData(69.toDuration(DurationUnit.MINUTES)),
            UserSettings()
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun CrampedPreview(){
    HDOpenTheme {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.CRAMPED.asData(69.toDuration(DurationUnit.MINUTES)),
            UserSettings()
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun InHidingPreview(){
    HDOpenTheme {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.IN_HIDING.asData(69.toDuration(DurationUnit.MINUTES)),
            UserSettings()
        )
    }
}
