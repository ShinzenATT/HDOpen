package se.gorymoon.hdopen.dto

import kotlinx.datetime.Instant
import kotlin.time.Duration

data class DoorData(
    val status: DoorStatus,
    val duration: Duration? = null,
    val timestamp: Instant? = null,
    var isLoading: Boolean = false
)