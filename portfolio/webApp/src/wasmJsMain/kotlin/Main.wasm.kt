
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import example.portfolio.style.PortfolioTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Portfolio") {
        PortfolioTheme {
            PortfolioWeb()
        }
    }
}
