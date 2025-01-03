package se.gorymoon.hdopen.services

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import se.gorymoon.hdopen.dto.UserSettings
import se.gorymoon.hdopen.ui.models.SettingsState

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    suspend fun storeSettings(settings: UserSettings = SettingsState.value) {
        dataStore.edit { preferences ->
            Log.d("SettingsRepository", "storing settings $settings")
            preferences[ONBOARDING_COMPLETED] = settings.onboardingCompleted
            preferences[VIEW_MUSIC] = settings.showMusic
            preferences[VIEW_PRESENCE] = settings.showVisitors
            preferences[VIEW_ADS] = settings.adsActive
            preferences[DEVICE_THEME] = settings.useDeviceTheme
        }
    }

    val userSettings: Flow<UserSettings> = dataStore.data.map { preferences ->
        Log.d("SettingsRepository", "Retrieving settings")
        UserSettings(
            onboardingCompleted = preferences[ONBOARDING_COMPLETED] == true,
            showMusic = preferences[VIEW_MUSIC] == true,
            showVisitors = preferences[VIEW_PRESENCE] == true,
            adsActive = preferences[VIEW_ADS] == true,
            useDeviceTheme = preferences[DEVICE_THEME] == true,
        )
    }

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        private val VIEW_MUSIC = booleanPreferencesKey("view_music")
        private val VIEW_PRESENCE = booleanPreferencesKey("view_presence")
        private val VIEW_ADS = booleanPreferencesKey("view_ads")
        private val DEVICE_THEME = booleanPreferencesKey("device_theme")
    }
}