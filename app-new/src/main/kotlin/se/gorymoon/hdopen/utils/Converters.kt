package se.gorymoon.hdopen.utils

import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.dto.NowPlayingData
import se.gorymoon.hdopen.dto.PresenceData
import se.gorymoon.hdopen.dto.network.ChassitResponse
import se.gorymoon.hdopen.dto.network.NowPlayingResponse
import se.gorymoon.hdopen.dto.network.SalePresenceResponse
import kotlin.time.Duration

fun DoorStatus.asData(duration: Duration? = null, isLoading: Boolean = false)
    = DoorData(this, duration, isLoading)



fun ChassitResponse.asDoorData(): DoorData {
    return when{
        door.status == true && presence.totalVisitors > 6 -> DoorStatus.CRAMPED.asData(door.durationTime)
        door.status == true && presence.totalVisitors > 4 -> DoorStatus.BUSY.asData(door.durationTime)
        door.status == true -> DoorStatus.OPEN.asData(door.durationTime)
        door.status == false && (door.durationTime > media.duration || door.durationTime > presence.duration) -> DoorStatus.IN_HIDING.asData(door.durationTime)
        door.status == false -> DoorStatus.CLOSED.asData(door.durationTime)
        door.status == null -> DoorStatus.UNKNOWN.asData(door.durationTime)
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