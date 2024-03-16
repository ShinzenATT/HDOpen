package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Job
import se.gorymoon.hdopen.dto.network.AdResponse

object AdState: MutableState<AdResponse?> by mutableStateOf(null){
    lateinit var cycleJob: Job
}