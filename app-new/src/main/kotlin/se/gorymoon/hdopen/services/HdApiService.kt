package se.gorymoon.hdopen.services

import android.util.Log
import se.gorymoon.hdopen.BuildConfig
import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.dto.network.ChassitResponse
import se.gorymoon.hdopen.dto.network.DoorResponse
import se.gorymoon.hdopen.utils.KnownApi
import se.gorymoon.hdopen.utils.WebClient
import se.gorymoon.hdopen.utils.asData

fun doDoorRequest() = WebClient.get<DoorResponse>(KnownApi.HD, "door")
fun doChassitInfoRequest() = WebClient.get<ChassitResponse>(KnownApi.HD, "door/full"){
    headers["Authorization"] = BuildConfig.HD_API_KEY
}

suspend fun getDoorData(): DoorData {
    val res: DoorResponse = doDoorRequest().await()

    return when (res.status) {
        true -> DoorStatus.OPEN.asData(res.durationTime)
        false -> DoorStatus.CLOSED.asData(res.durationTime)
        null -> DoorStatus.UNKNOWN.asData(res.durationTime)
    }
}

suspend fun getChassitData(): ChassitResponse {
    val res: ChassitResponse = doChassitInfoRequest().await()
    return res
}
