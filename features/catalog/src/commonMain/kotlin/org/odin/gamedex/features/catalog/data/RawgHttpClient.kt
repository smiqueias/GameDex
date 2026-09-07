package org.odin.gamedex.features.catalog.data


import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.http.parameters
import org.odin.gamedex.core.network.createHttpClient
import org.odin.gamedex.core.network.BuildKonfig

internal val rawgHttpClient: HttpClient by lazy {
    createHttpClient {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.rawg.io"
                encodedPath = "/api/" + encodedPath.removePrefix("/")
                parameters.append("key", BuildKonfig.RAWG_API_KEY)}
            }
        }
    }