package org.odin.gamedex.features.catalog.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<GameDto>
)

@Serializable
data class GameDto(
    val id: Int,
    val name: String,
    @SerialName("background_image") val backgroundImage: String? = null,
    val released: String? = null,
    val rating: Double = 0.0,
    val metacritic: Int? = null,
    val genres: List<GenreDto> = emptyList(),
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String,
    val slug: String,
)