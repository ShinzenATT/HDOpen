package se.gorymoon.hdopen.dto.network

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Serializable
data class SalePresenceResponse(
    val totalVisitors: Int,
    val activeCount: Int,
    val inactiveCount: Int,
    val sittandeCount: Int,
    val lastBuyTimestamp: Long,
    val durationSinceBuy: Long
) {
    val lastUpdated = Instant.fromEpochSeconds(lastBuyTimestamp)
    val duration: Duration = durationSinceBuy.toDuration(DurationUnit.SECONDS)
}
