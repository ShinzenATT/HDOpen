package se.gorymoon.hdopen.dto

import android.os.Build

data class UserSettings(
    val useDeviceTheme: Boolean = false /*Build.VERSION.SDK_INT >= Build.VERSION_CODES.S*/,
    val adsActive: Boolean = true,
    val showMusic: Boolean = true,
    val showVisitors: Boolean = true
)
