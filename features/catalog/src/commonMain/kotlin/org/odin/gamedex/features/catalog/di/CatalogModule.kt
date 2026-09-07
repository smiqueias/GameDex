package org.odin.gamedex.features.catalog.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.odin.gamedex.core.network.di.RAWG_CLIENT
import org.odin.gamedex.features.catalog.data.datasource.GameRemoteDataSource
import org.odin.gamedex.features.catalog.data.repository.GameRepositoryImpl
import org.odin.gamedex.features.catalog.domain.GameRepository
import org.odin.gamedex.features.catalog.domain.GetGamesUseCase
import org.odin.gamedex.features.catalog.ui.CatalogViewModel

val catalogModule = module {
    single { GameRemoteDataSource(get(RAWG_CLIENT)) }
    single<GameRepository> { GameRepositoryImpl(get()) }
    factory { GetGamesUseCase(get()) }
    viewModel { CatalogViewModel(get()) }
}