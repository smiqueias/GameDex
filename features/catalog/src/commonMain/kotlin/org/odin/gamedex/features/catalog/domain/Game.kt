package org.odin.gamedex.features.catalog.domain

data class Game(
    val id: Int,
    val name: String,
    val coverUrl: String?,
    val releaseDate: String?,
    val rating: Double,
    val metacritic: Int?,
    val genres: List<String>,
)
