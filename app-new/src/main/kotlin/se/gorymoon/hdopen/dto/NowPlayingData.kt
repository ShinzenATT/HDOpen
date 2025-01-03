package se.gorymoon.hdopen.dto

import kotlin.time.Duration

data class NowPlayingData(val song: String?, val artist: String?, val duration: Duration){
}