package org.odin.gamedex.features.catalog.ui

import org.odin.gamedex.features.catalog.domain.Game

data class CatalogUiState(
    val games: List<Game> = emptyList(),
    val searchQuery: String = "",
)

sealed interface CatalogUiAction {
    data object LoadGames : CatalogUiAction
    data class Search(val query: String) : CatalogUiAction
    data class OnGameClicked(val gameId: Int) : CatalogUiAction
}

sealed interface CatalogSideEffect {
    data class NavigateToDetail(val gameId: Int) : CatalogSideEffect
}