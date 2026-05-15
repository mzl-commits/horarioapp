package com.example.horarioapp.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BrandOrange,
    onPrimary = Color.White,
    primaryContainer = CoralLight,
    onPrimaryContainer = CoralDark,

    secondary = BrandDarkBlue,
    onSecondary = Color.White,
    secondaryContainer = GrayLight,
    onSecondaryContainer = GrayDark,

    tertiary = YellowAccent,
    onTertiary = TextPrimary,
    tertiaryContainer = YellowLight,
    onTertiaryContainer = YellowDark,

    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = ErrorRed,

    background = BackgroundDefault,
    onBackground = TextPrimary,

    surface = SurfaceLight,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceMedium,
    onSurfaceVariant = TextSecondary,

    outline = DividerColor,
    outlineVariant = Color(0xFFCAC4BE),
)

private val DarkColorScheme = darkColorScheme(
    primary = CoralLight,
    onPrimary = CoralDark,
    primaryContainer = CoralDark,
    onPrimaryContainer = CoralLight,

    secondary = GrayLight,
    onSecondary = GrayDark,
    secondaryContainer = GrayDark,
    onSecondaryContainer = GrayLight,

    tertiary = YellowLight,
    onTertiary = YellowDark,
    tertiaryContainer = YellowDark,
    onTertiaryContainer = YellowLight,

    error = Color(0xFFFFB4AB),
    onError = ErrorRed,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = Color(0xFF1A1A1A),
    onBackground = Color.White,

    surface = Color(0xFF262626),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF49454E),
    onSurfaceVariant = Color(0xFFCAC4BE),

    outline = Color(0xFF938F85),
    outlineVariant = Color(0xFF49454E),
)

@Composable
fun HorarioAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}