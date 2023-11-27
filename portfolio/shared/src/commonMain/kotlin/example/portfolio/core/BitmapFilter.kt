package example.portfolio.core

import androidx.compose.ui.graphics.ImageBitmap

interface BitmapFilter {
    fun apply(bitmap: ImageBitmap): ImageBitmap
}
