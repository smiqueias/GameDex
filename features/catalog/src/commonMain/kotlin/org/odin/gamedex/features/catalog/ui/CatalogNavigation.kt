package org.odin.gamedex.features.catalog.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.odin.gamedex.core.navigation.Routes

fun NavGraphBuilder.catalogGraph(navController: NavController) {
    composable<Routes.Catalog> {
        CatalogScreen(
            onNavigateToDetail = { gameId ->
                // navController.navigate(Routes.Detail(gameId)) — plugamos quando a feature:detail existir
            }
        )
    }
}