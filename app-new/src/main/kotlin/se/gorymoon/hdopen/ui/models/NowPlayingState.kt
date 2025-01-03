package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.datetime.Clock
import se.gorymoon.hdopen.dto.NowPlayingData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object NowPlayingState: MutableState<NowPlayingData> by mutableStateOf(NowPlayingData(null, null, 0.toDuration(DurationUnit.SECONDS))) {

}