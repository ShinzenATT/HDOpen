package se.gorymoon.hdopen.dto.network

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Serializable
data class NowPlayingResponse(val nowPlaying: NowPlayingMedia?, val lastUpdatedTimestamp: Long, val durationSinceUpdate: Long){
    val lastUpdated = Instant.fromEpochSeconds(lastUpdatedTimestamp)
    val duration: Duration = durationSinceUpdate.toDuration(DurationUnit.SECONDS)

    @Serializable
    data class NowPlayingMedia(val title: String, val artist: String)
}
