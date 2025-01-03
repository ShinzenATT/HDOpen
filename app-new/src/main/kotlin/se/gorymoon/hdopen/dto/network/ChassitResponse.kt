package se.gorymoon.hdopen.dto.network

import kotlinx.serialization.Serializable

@Serializable
data class ChassitResponse(val door: DoorResponse, val media: NowPlayingResponse, val presence: SalePresenceResponse)
