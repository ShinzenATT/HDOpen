package se.gorymoon.hdopen.ui.viewmodels

import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import se.gorymoon.hdopen.dto.DoorData
import se.gorymoon.hdopen.services.doAdRequest
import se.gorymoon.hdopen.services.getDoorData
import se.gorymoon.hdopen.ui.models.AdState
import se.gorymoon.hdopen.ui.models.DoorState
import se.gorymoon.hdopen.utils.launchTask

private const val TAG = "Home View Model"

fun refreshDoorState(): Job{
    Log.d(TAG, "Refreshing door status")
    if(!DoorState.isLoading)
        DoorState.value = DoorData(DoorState.status, isLoading = true)
    return launchTask {
        val data = getDoorData()
        DoorState.value = data
        Log.d(TAG, "Set door state to " + data.status.name)
    }
}

suspend fun refreshAd() {
    Log.d(TAG, "Refreshing ad")
    val ad = doAdRequest().await()
    AdState.value = ad
}

fun startAdRotation() = launchTask {
    Log.d(TAG, "Starting ad rotation")
    while (isActive){
        refreshAd()
        delay(7000)
    }
}
