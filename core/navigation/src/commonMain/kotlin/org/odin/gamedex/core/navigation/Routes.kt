package org.odin.gamedex.core.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Catalog : Routes

    @Serializable
    data class Detail(val movieId: Int) : Routes
}