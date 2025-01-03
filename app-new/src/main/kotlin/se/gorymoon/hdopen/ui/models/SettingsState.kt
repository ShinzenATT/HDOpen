package se.gorymoon.hdopen.ui.models

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import se.gorymoon.hdopen.AppConfig
import se.gorymoon.hdopen.dto.UserSettings
import se.gorymoon.hdopen.services.SettingsRepository

object SettingsState: MutableState<UserSettings> by mutableStateOf(UserSettings()) {
    private lateinit var repository: SettingsRepository
    private val storedState: MutableStateFlow<UserSettings?> = MutableStateFlow(null)
    val useDeviceTheme get() =  value.useDeviceTheme

    fun saveSettings(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.storeSettings(value)
        }
    }

    fun ComponentActivity.awaitSettings() = runBlocking {
        storedState.filterNotNull().first()
    }

    fun AppConfig.setupPersistentSettings() {
        this@SettingsState.repository = this.settingsRepository

        CoroutineScope(Dispatchers.IO).launch {
            repository.userSettings.collect {
                Log.d("SettingsState", "settings loaded $it")
                storedState.value = it
                value = it
            }
        }
    }
}