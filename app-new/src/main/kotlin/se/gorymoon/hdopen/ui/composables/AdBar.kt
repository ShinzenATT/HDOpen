package se.gorymoon.hdopen.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import coil.compose.AsyncImage
import se.gorymoon.hdopen.ui.models.AdState

@Composable
fun AdBar(){
    val ad by remember { AdState }

    if(ad != null) {
        AsyncImage(model = ad!!.image, contentDescription = "Ad for ${ad!!.link}")
    }
}