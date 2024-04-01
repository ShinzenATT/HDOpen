package se.gorymoon.hdopen.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import coil.compose.AsyncImage
import se.gorymoon.hdopen.ui.models.AdState
import se.gorymoon.hdopen.ui.viewmodels.startAdRotation

@Composable
fun AdBar(){
    val ad by remember { AdState }

    DisposableEffect(Unit){
        if(AdState.cycleJob == null || AdState.cycleJob!!.isCancelled) {
            AdState.cycleJob = startAdRotation()
        }
        onDispose {
            AdState.cycleJob?.cancel()
        }
    }

    if(ad != null) {
        AsyncImage(model = ad!!.image, contentDescription = "Ad for ${ad!!.link}")
    }
}