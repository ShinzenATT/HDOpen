package se.gorymoon.hdopen.utils

import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import kotlin.time.Duration

fun DoorStatus.asData(duration: Duration? = null) = DoorData(this, duration)