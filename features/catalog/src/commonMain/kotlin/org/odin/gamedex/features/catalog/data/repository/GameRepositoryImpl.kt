package org.odin.gamedex.features.catalog.data.repository

import org.odin.gamedex.features.catalog.data.datasource.GameRemoteDataSource
import org.odin.gamedex.features.catalog.data.mapper.toDomain
import org.odin.gamedex.features.catalog.domain.Game
import org.odin.gamedex.features.catalog.domain.GameRepository

internal class GameRepositoryImpl(
    private val remoteDataSource: GameRemoteDataSource
) : GameRepository {

    override suspend fun getGames(page: Int, search: String?): List<Game> =
        remoteDataSource.getGames(page, search).results.map { it.toDomain() }
}