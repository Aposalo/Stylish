package gr.aposalo.stylish.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primaryContainer = WhiteSmoke,
    onPrimaryContainer = GraniteGray,
    secondary = RadicalRed,
    onSecondary = Color.White,
    outline = SpunPearl,
    surface = Color.White,
    onSurface = Violet,
    background = Color.White,
    onBackground = PaleRed
)

private val LightColorScheme = lightColorScheme(
    primaryContainer = WhiteSmoke,
    onPrimaryContainer = GraniteGray,
    secondary = RadicalRed,
    onSecondary = Color.White,
    outline = SpunPearl,
    surface = Color.White,
    onSurface = Violet,
    background = Color.White,
    onBackground = PaleRed
)

@Composable
fun StylishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}