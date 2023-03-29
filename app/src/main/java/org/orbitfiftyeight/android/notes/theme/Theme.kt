package org.orbitfiftyeight.android.notes.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
  primary = blue,
  primaryVariant = blueDark,
  secondary = blue
)

private val DarkThemeColors = darkColors(
  primary = Color(0xFF1A9EBC),
  primaryVariant = Color(0xFF97e0f1),
  secondary = red,
  onPrimary = Color.White,
)


/**
 * Responsible for switching color palette for dark and light theme.
 */
@Composable
fun NotesTheme(content: @Composable () -> Unit) {
  val isDarkThemeEnabled = isSystemInDarkTheme() || NotesThemeSettings.isDarkThemeEnabled
  val colors = if (isDarkThemeEnabled) DarkThemeColors else LightThemeColors

  MaterialTheme(colors = colors, content = content)
}

/**
 * Allows changing between light and a dark theme from the app's settings.
 */
object NotesThemeSettings {
  var isDarkThemeEnabled by mutableStateOf(false)
}
