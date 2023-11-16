
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import example.imageviewer.style.ImageViewerTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Portfolio") {
        ImageViewerTheme {
            ImageViewerWeb()
        }
    }
}
