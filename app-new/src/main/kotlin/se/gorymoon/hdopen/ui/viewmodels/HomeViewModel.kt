package se.gorymoon.hdopen.ui.viewmodels

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.services.doAdRequest
import se.gorymoon.hdopen.services.getDoorData
import se.gorymoon.hdopen.ui.models.AdState
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.utils.asData
import se.gorymoon.hdopen.utils.launchTask

fun refreshDoorState(){
    DoorState.value = DoorStatus.LOADING.asData()
    launchTask {
        val data = getDoorData()
        DoorState.value = data
    }
}

suspend fun refreshAd() {
    val ad = doAdRequest().await()
    AdState.value = ad
}

fun startAdRotation() = launchTask {
    while (isActive){
        refreshAd()
        delay(7000)
    }
}
