package org.odin.gamedex.features.catalog.domain

interface GameRepository {
    suspend fun getGames(page: Int = 1, search: String? = null): List<Game>
}