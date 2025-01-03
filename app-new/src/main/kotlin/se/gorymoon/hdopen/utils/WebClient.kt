package se.gorymoon.hdopen.utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import se.gorymoon.hdopen.BuildConfig

object WebClient {
    private val client = createClient()
    private val getConfig: HttpRequestBuilder.() -> Unit = {
        headers["Accepts"] = "application/json"
    }

    suspend fun get(url: String, block: HttpRequestBuilder.() -> Unit = {})
    = client.get(url) {
        getConfig()
        block()
    }

    inline fun <reified T> get(url: String, noinline block: HttpRequestBuilder.() -> Unit = {})
    = runAsync { get(url, block).body<T>() }

    inline fun <reified T> get(
        host: KnownApi,
        path: String,
       noinline block: HttpRequestBuilder.() -> Unit = {}
    ) = get<T>(host.host + path, block)

    fun createClient(block: HttpClientConfig<*>. () -> Unit = {}) = HttpClient(){
        install(ContentNegotiation){
            json()
        }
        block()
    }
}

enum class KnownApi (val host: String){
    HD(BuildConfig.HD_API_URL),
    GORYMOON(BuildConfig.GORYMOON_API_URL)
}
