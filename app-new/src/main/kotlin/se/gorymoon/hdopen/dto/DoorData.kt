package se.gorymoon.hdopen.dto

import kotlin.time.Duration

data class DoorData(
    val status: DoorStatus,
    val duration: Duration? = null,
    var isLoading: Boolean = false
)