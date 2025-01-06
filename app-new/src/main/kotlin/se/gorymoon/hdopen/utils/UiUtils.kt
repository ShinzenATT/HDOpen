package se.gorymoon.hdopen.utils


import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.glance.unit.ColorProvider


@SuppressLint("RestrictedApi")
fun Color.asProvider() = ColorProvider(this)

val DpSize.minSize: Dp get() {
    return if(width < height) {
        width
    } else {
        height
    }
}
