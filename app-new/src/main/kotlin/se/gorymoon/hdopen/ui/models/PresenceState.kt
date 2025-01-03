package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.datetime.Clock
import se.gorymoon.hdopen.dto.PresenceData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object PresenceState: MutableState<PresenceData> by mutableStateOf(PresenceData(0,0,0,0, 0.toDuration(DurationUnit.SECONDS))) {
}