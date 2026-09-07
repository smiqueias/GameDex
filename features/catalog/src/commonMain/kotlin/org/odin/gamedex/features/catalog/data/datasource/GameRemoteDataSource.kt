package org.odin.gamedex.features.catalog.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.odin.gamedex.core.network.safeApiCall
import org.odin.gamedex.features.catalog.data.dto.GameDto
import org.odin.gamedex.features.catalog.data.dto.GamesResponseDto

internal class GameRemoteDataSource(private val client: HttpClient) {

    suspend fun getGames(page: Int, search: String?): GamesResponseDto = safeApiCall {
        client.get("games") {
            parameter("page", page)
            search?.let { parameter("search", it) }
        }.body()
    }

    suspend fun getGameDetail(id: Int): GameDto = safeApiCall {
        client.get("games/$id").body()
    }
}