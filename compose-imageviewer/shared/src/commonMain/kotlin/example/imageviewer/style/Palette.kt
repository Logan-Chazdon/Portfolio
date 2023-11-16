package example.imageviewer.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

object ImageviewerColors {
    val Gray = Color.DarkGray
    val LightGray = Color(100, 100, 100)
    val DarkGray = Color(32, 32, 32)
    val PreviewImageAreaHoverColor = Color(45, 45, 45)
    val ToastBackground = Color(23, 23, 23)
    val MiniatureColor = Color(50, 50, 50)
    val MiniatureHoverColor = Color(55, 55, 55)
    val Foreground = Color(210, 210, 210)
    val TranslucentBlack = Color(0, 0, 0, 60)
    val TranslucentWhite = Color(255, 255, 255, 20)
    val Transparent = Color.Transparent

    val background = Color(0xFFFFFFFF)
    val onBackground = Color(0xFF19191C)

    val fullScreenImageBackground = Color(0xFF19191C)

    val uiLightBlack = Color(25, 25, 28, 180)
    val textOnImage = Color.White
    val noteBlockBackground = Color(0xFFF3F3F4)


    val KotlinGradient0 = Color(0xFF7F52FF)
    val KotlinGradient50 = Color(0xFFC811E2)
    val KotlinGradient100 = Color(0xFFE54857)

    val kotlinHorizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(
            KotlinGradient0,
            KotlinGradient50,
            KotlinGradient100
        )
    )

    fun buttonBackground(isHover: Boolean) = if (isHover) TranslucentBlack else Transparent
}


@Composable
fun ImageViewerTheme(content: @Composable () -> Unit) {
    isSystemInDarkTheme() // todo check and change colors
    val Purple500 = Color(0xFF6200EE)
    val Purple700 = Color(0xFF3700B3)
    val Teal200 = Color(0xFF03DAC5)
    val Teal700 = Color(0xFF368D85)
    val DarkColorPalette = darkColors(
        primary = Teal700,
        primaryVariant = Teal200,
        background = Color.DarkGray,
        surface = Color.DarkGray,
        onPrimary = Color.White,
    )
    MaterialTheme(
        colorScheme = if(true) {
            darkColorScheme(
                surfaceVariant = Color(red = 24, green = 23, blue = 27)
            )
        } else {
            MaterialTheme.colorScheme.copy(
                background = ImageviewerColors.background,
                onBackground = ImageviewerColors.onBackground
            )
        }
    ) {
        ProvideTextStyle(LocalTextStyle.current.copy(letterSpacing = 0.sp)) {
            content()
        }
    }
}
