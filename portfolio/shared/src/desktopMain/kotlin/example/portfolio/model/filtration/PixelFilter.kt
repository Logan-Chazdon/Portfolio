package example.portfolio.model.filtration

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.toComposeImageBitmap
import example.portfolio.core.BitmapFilter
import example.portfolio.utils.applyPixelFilter

class PixelFilter : BitmapFilter {

    override fun apply(bitmap: ImageBitmap): ImageBitmap {
        return applyPixelFilter(bitmap.toAwtImage()).toComposeImageBitmap()
    }
}
