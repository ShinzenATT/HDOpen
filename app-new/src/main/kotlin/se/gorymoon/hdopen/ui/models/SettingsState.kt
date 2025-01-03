package se.gorymoon.hdopen.ui.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import se.gorymoon.hdopen.dto.UserSettings

object SettingsState: MutableState<UserSettings> by mutableStateOf(UserSettings()) {
    val useDeviceTheme get() =  value.useDeviceTheme
}