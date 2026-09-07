package org.odin.gamedex.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CineDarkColorScheme = darkColorScheme(
    background = CineBackground,
    surface = CineSurface,
    primary = CinePrimary,
    secondary = CineAccentRed,
    tertiary = CineAccentYellow,
    onBackground = CineTextPrimary,
    onSurface = CineTextPrimary,
    onPrimary = CineTextPrimary,
)

private val CineLightColorScheme = lightColorScheme(
    primary = CinePrimary,
    secondary = CineAccentRed,
    tertiary = CineAccentYellow,
)

@Composable
fun GameDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) CineDarkColorScheme else CineLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CineTypography,
        content = content
    )
}