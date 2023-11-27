package example.portfolio.model.filtration

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.toComposeImageBitmap
import example.portfolio.core.BitmapFilter
import example.portfolio.utils.applyBlurFilter

class BlurFilter : BitmapFilter {

    override fun apply(bitmap: ImageBitmap): ImageBitmap {
        return applyBlurFilter(bitmap.toAwtImage()).toComposeImageBitmap()
    }
}
