package se.gorymoon.hdopen.utils

import kotlinx.datetime.Instant
import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.dto.NowPlayingData
import se.gorymoon.hdopen.dto.PresenceData
import se.gorymoon.hdopen.dto.network.ChassitResponse
import se.gorymoon.hdopen.dto.network.NowPlayingResponse
import se.gorymoon.hdopen.dto.network.SalePresenceResponse
import kotlin.time.Duration

fun DoorStatus.asData(duration: Duration? = null, timestamp: Instant? = null, isLoading: Boolean = false)
    = DoorData(this, duration, timestamp, isLoading)



fun ChassitResponse.asDoorData(): DoorData {
    return when{
        door.status == true && presence.totalVisitors > 6 -> DoorStatus.CRAMPED.asData(door.durationTime, door.instant)
        door.status == true && presence.totalVisitors > 4 -> DoorStatus.BUSY.asData(door.durationTime, door.instant)
        door.status == true -> DoorStatus.OPEN.asData(door.durationTime, door.instant)
        door.status == false && (door.durationTime > media.duration || door.durationTime > presence.duration) -> DoorStatus.IN_HIDING.asData(door.durationTime, door.instant)
        door.status == false -> DoorStatus.CLOSED.asData(door.durationTime, door.instant)
        door.status == null -> DoorStatus.UNKNOWN.asData(door.durationTime, door.instant)
        else -> throw IllegalStateException("Door data from api contained invalid data")
    }
}

fun NowPlayingResponse.asNowPlayingData() = NowPlayingData(
    this.nowPlaying?.title,
    this.nowPlaying?.artist,
    this.duration
)

fun SalePresenceResponse.asPresenceData() = PresenceData(
    this.totalVisitors,
    this.activeCount,
    this.inactiveCount,
    this.sittandeCount,
    this.duration
)