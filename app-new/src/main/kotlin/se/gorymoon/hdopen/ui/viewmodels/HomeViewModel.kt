package se.gorymoon.hdopen.ui.viewmodels

import se.gorymoon.hdopen.dto.DoorStatus
import se.gorymoon.hdopen.services.getDoorData
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