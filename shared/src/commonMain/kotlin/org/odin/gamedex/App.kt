package org.odin.gamedex


import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration
import org.odin.gamedex.core.designsystem.GameDexTheme
import org.odin.gamedex.core.navigation.Routes
import org.odin.gamedex.core.network.di.networkModule
import org.odin.gamedex.features.catalog.di.catalogModule
import org.odin.gamedex.features.catalog.ui.catalogGraph

@Composable
fun App() {
    KoinApplication(configuration = koinConfiguration(declaration = {
        modules(
            networkModule,
            catalogModule
        )
    }), content = {
        GameDexTheme {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.Catalog) {
                catalogGraph(navController)
            }
        }
    })
}