package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import se.gorymoon.hdopen.dto.network.AdResponse

object AdState: MutableState<AdResponse?> by mutableStateOf(null)