package se.gorymoon.hdopen.utils

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun launchTask(
    dispatcher: CoroutineContext = Dispatchers.Default,
    block: suspend CoroutineScope.() -> Unit
) = CoroutineScope(dispatcher).launch(block = block)

fun runAsync(
    dispatcher: CoroutineContext = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
) = CoroutineScope(dispatcher).async(block = block)

fun <T> Deferred<T>.awaitSync() = runBlocking { this@awaitSync.await() }

fun Job.joinSync() = runBlocking { this@joinSync.join() }
