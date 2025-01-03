package se.gorymoon.hdopen.dto

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration

class PresenceData(val totalPeople: Int, val activePeople: Int, val inactivePeople: Int, val sittande: Int, val timeSinceBuy: Duration) {
}