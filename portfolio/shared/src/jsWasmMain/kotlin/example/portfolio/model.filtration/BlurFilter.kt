package example.portfolio.model.filtration

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import example.portfolio.core.BitmapFilter
import example.portfolio.utils.applyBlurFilter

class BlurFilter : BitmapFilter {
	override fun apply(bitmap: ImageBitmap): ImageBitmap {
		return applyBlurFilter(bitmap.asSkiaBitmap()).asComposeImageBitmap()
	}
}