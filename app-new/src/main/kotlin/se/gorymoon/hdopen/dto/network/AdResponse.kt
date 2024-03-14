package se.gorymoon.hdopen.dto.network

import coil.request.ImageRequest
import kotlinx.serialization.Serializable

@Serializable
data class AdResponse(val image: String, val link: String)
