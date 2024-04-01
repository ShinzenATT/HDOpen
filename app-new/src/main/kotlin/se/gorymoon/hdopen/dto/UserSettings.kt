package se.gorymoon.hdopen.dto

import android.os.Build

data class UserSettings(
    val useDeviceTheme: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    val apiToken: String? = null,
    val adsActive: Boolean = true,
    val showMusic: Boolean = true,
    val showVisitors: Boolean = true
){
    val hasApiKey get() = apiToken != null
    fun modifyCopy(
        useDeviceTheme: Boolean? = null,
        apiToken: String? = null,
        adsActive: Boolean? = null,
        showMusic: Boolean? = null,
        showVisitors: Boolean? = null
    ) = UserSettings(
        useDeviceTheme ?: this.useDeviceTheme,
            apiToken ?: this.apiToken,
            adsActive ?: this.adsActive,
        showMusic ?: this.showMusic,
        showVisitors ?: this.showVisitors
        )
}
