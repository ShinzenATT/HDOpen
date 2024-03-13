package se.gorymoon.hdopen.services

import se.gorymoon.hdopen.dto.network.DoorResponse
import se.gorymoon.hdopen.utils.KnownApi
import se.gorymoon.hdopen.utils.WebClient

fun getDoorData() = WebClient.get<DoorResponse>(KnownApi.HD, "door")