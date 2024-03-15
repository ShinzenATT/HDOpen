package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.utils.asData
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object DoorState: MutableState<DoorData> by mutableStateOf(DoorStatus.UNKNOWN.asData(isLoading = true)){
    val status get() = value.status
    val duration get() = value.duration
    val isLoading get() = value.isLoading

    fun cycleState(){
        val entries = DoorStatus.entries
        val i = (status.ordinal + 1) % entries.size
        value = DoorData(entries[i], 30.toDuration(DurationUnit.MINUTES))
    }
}