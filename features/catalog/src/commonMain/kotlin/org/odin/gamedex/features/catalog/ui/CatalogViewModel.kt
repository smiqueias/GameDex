package org.odin.gamedex.features.catalog.ui

import org.odin.gamedex.core.common.mvi.BaseViewModel
import org.odin.gamedex.core.common.mvi.uieffect.launchWithUiEffect
import org.odin.gamedex.features.catalog.domain.GetGamesUseCase

internal class CatalogViewModel(
    private val getGamesUseCase: GetGamesUseCase,
) : BaseViewModel<CatalogUiState, CatalogUiAction, CatalogSideEffect>(CatalogUiState()) {

    init {
        onAction(CatalogUiAction.LoadGames)
    }

    override fun handleAction(action: CatalogUiAction) {
        when (action) {
            CatalogUiAction.LoadGames -> loadGames()
            is CatalogUiAction.Search -> {
                updateState { copy(searchQuery = action.query) }
                loadGames()
            }
            is CatalogUiAction.OnGameClicked -> sendEffect(CatalogSideEffect.NavigateToDetail(action.gameId))
        }
    }

    private fun loadGames() {
        launchWithUiEffect {
            val games = getGamesUseCase(search = currentState.searchQuery.ifBlank { null })
            updateState { copy(games = games) }
        }
    }
}