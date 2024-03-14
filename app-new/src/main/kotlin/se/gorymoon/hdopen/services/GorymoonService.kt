package se.gorymoon.hdopen.services

import se.gorymoon.hdopen.dto.network.AdResponse
import se.gorymoon.hdopen.utils.KnownApi
import se.gorymoon.hdopen.utils.WebClient

fun doAdRequest()
    = WebClient.get<AdResponse>(KnownApi.GORYMOON, "ad")


