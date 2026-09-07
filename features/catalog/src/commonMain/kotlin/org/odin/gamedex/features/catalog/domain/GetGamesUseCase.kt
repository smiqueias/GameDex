package org.odin.gamedex.features.catalog.domain

class GetGamesUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(page: Int = 1, search: String? = null): List<Game> =
        repository.getGames(page, search)
}