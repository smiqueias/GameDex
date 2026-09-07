package org.odin.gamedex.core.network.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.odin.gamedex.core.network.BuildKonfig
import org.odin.gamedex.core.network.createHttpClient
val RAWG_CLIENT = named("rawgClient")

val networkModule = module {
    single { createHttpClient() }

    single(RAWG_CLIENT) {
        get<HttpClient>().config {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.rawg.io"
                    encodedPath = "/api/" + encodedPath.removePrefix("/")
                    parameters.append("key", BuildKonfig.RAWG_API_KEY)
                }
            }
        }
    }
}