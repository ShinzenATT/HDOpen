package se.gorymoon.hdopen.services

import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.dto.network.DoorResponse
import se.gorymoon.hdopen.utils.KnownApi
import se.gorymoon.hdopen.utils.WebClient
import se.gorymoon.hdopen.utils.asData

fun doDoorRequest() = WebClient.get<DoorResponse>(KnownApi.HD, "door")

suspend fun getDoorData(): DoorData {
    val res: DoorResponse = doDoorRequest().await()

    return when{
        res.status == true -> DoorStatus.OPEN.asData(res.durationTime)
        res.status == false -> DoorStatus.CLOSED.asData(res.durationTime)
        res.status == null -> DoorStatus.UNKNOWN.asData(res.durationTime)
        else -> throw IllegalStateException("HD door response contained invalid data")
    }
}
