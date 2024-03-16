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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.ui.theme.HDOpenTheme
import se.gorymoon.hdopen.ui.viewmodels.refreshDoorState
import se.gorymoon.hdopen.utils.asData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun DoorDisplay(padding: PaddingValues, state: DoorData){
    val (status, duration, isLoading) = state
    val displayText = if(isLoading) "Loading..." else status.text

    // A surface container using the 'background' color from the theme
    Surface(
        color = status.containerColor(),
        modifier = Modifier.fillMaxSize().clickable { refreshDoorState() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = displayText,
                color = status.textColor(),
                fontSize = 15.em,
                fontWeight = FontWeight.Bold
            )
            if (isLoading){
                LinearProgressIndicator(color = status.textColor())
            } else if(duration != null){
                DurationText(duration)
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
            DoorStatus.OPEN.asData(69.toDuration(DurationUnit.MINUTES))
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
            DoorStatus.CLOSED.asData(69.toDuration(DurationUnit.MINUTES))
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun UnknownPreview(){
    HDOpenTheme(dynamicColor = false) {
        DoorDisplay(
            PaddingValues(0.dp),
            DoorStatus.UNKNOWN.asData(69.toDuration(DurationUnit.MINUTES))
        )
    }
}
