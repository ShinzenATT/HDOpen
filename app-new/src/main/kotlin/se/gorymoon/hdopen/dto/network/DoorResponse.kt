package se.gorymoon.hdopen.dto.network

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Serializable
data class DoorResponse(
    val status: Boolean?,
    val updated: String,
    val duration: Long,
    val duration_str: String
) {
    val durationTime get() = duration.toDuration(DurationUnit.SECONDS)
    val timestamp = Instant.fromEpochSeconds(Clock.System.now().epochSeconds - duration)
}
