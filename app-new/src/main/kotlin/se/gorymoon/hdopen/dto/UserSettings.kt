package se.gorymoon.hdopen.dto

data class UserSettings(
    val useDeviceTheme: Boolean = false /*Build.VERSION.SDK_INT >= Build.VERSION_CODES.S*/,
    val adsActive: Boolean = true,
    val showMusic: Boolean = true,
    val showVisitors: Boolean = true,
    val onboardingCompleted: Boolean = false
)
