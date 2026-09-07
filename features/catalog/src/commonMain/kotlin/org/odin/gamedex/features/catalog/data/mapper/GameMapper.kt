package org.odin.gamedex.features.catalog.data.mapper

import org.odin.gamedex.features.catalog.data.dto.GameDto
import org.odin.gamedex.features.catalog.domain.Game

fun GameDto.toDomain(): Game = Game(
    id = id,
    name = name,
    coverUrl = backgroundImage,
    releaseDate = released,
    rating = rating,
    metacritic = metacritic,
    genres = genres.map { it.name },
)