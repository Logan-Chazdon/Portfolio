package example.portfolio.model.filtration

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import example.portfolio.core.BitmapFilter
import example.portfolio.utils.applyPixelFilter

class PixelFilter : BitmapFilter {
    override fun apply(bitmap: ImageBitmap): ImageBitmap =
        applyPixelFilter(bitmap.asAndroidBitmap()).asImageBitmap()
}
