package org.odin.cinekmp.core.network

import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import org.odin.cinekmp.core.common.errors.NetworkingError

suspend inline fun <T> safeApiCall(crossinline block: suspend () -> T): T {
    return try {
        block()
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpRequestTimeoutException) {
        throw NetworkingError.Timeout
    } catch (e: ResponseException) {
        val serverMessage = runCatching { e.response.body<String>() }.getOrNull()
        throw NetworkingError.ServerError(
            code = e.response.status.value,
            serverMessage = serverMessage
        )
    } catch (e: SerializationException) {
        throw NetworkingError.SerializationError(e)
    } catch (e: NetworkingError) {
        throw e
    } catch (e: Exception) {
        throw NetworkingError.Unknown(e)
    }
}