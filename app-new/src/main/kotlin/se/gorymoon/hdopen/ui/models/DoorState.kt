package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import se.gorymoon.hdopen.dto.DoorStatus

object DoorState: MutableState<DoorStatus> by mutableStateOf(DoorStatus.LOADING){
    fun cycleState(){
        val entries = DoorStatus.entries
        val i = (value.ordinal + 1) % entries.size
        value = entries[i]
    }
}