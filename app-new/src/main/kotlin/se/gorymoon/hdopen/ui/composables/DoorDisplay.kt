package se.gorymoon.hdopen.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.ui.theme.HDOpenTheme

@Composable
fun DoorDisplay(padding: PaddingValues, stateParam: MutableState<DoorStatus> = DoorState){
    val (state, setState) = remember { stateParam }
    // A surface container using the 'background' color from the theme
    Surface(color = state.containerColor(), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = state.text, color = state.textColor())
            Text("For a while")
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun OpenPreview(){
    HDOpenTheme {
        DoorDisplay(PaddingValues(0.dp), mutableStateOf(DoorStatus.OPEN))
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun ClosedPreview(){
    HDOpenTheme {
        DoorDisplay(PaddingValues(0.dp), mutableStateOf(DoorStatus.CLOSED))
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun LoadingPreview(){
    HDOpenTheme {
        DoorDisplay(PaddingValues(0.dp), mutableStateOf(DoorStatus.LOADING))
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
private fun UnknownPreview(){
    HDOpenTheme {
        DoorDisplay(PaddingValues(0.dp), mutableStateOf(DoorStatus.UNKNOWN))
    }
}
