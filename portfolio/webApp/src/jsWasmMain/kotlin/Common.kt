
import androidx.compose.runtime.*
import example.portfolio.*
import example.portfolio.core.BitmapFilter
import example.portfolio.core.FilterType
import example.portfolio.model.WrappedHttpClient
import example.portfolio.model.filtration.BlurFilter
import example.portfolio.model.filtration.GrayScaleFilter
import example.portfolio.model.filtration.PixelFilter
import example.portfolio.utils.ioDispatcher
import example.portfolio.view.ToastState
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.ExperimentalResourceApi

expect fun createWrappedHttpClient(): WrappedHttpClient

@Composable
internal fun PortfolioWeb() {
    val toastState = remember { mutableStateOf<ToastState>(ToastState.Hidden) }
    val ioScope: CoroutineScope = rememberCoroutineScope { ioDispatcher }
    val dependencies = remember(ioScope) { getDependencies(ioScope, toastState) }

    PortfolioCommon()
}

@OptIn(ExperimentalResourceApi::class)
fun getDependencies(ioScope: CoroutineScope, toastState: MutableState<ToastState>) = object : Dependencies {
    override val ioScope: CoroutineScope = ioScope
    override fun getFilter(type: FilterType): BitmapFilter = when (type) {
        FilterType.GrayScale -> GrayScaleFilter()
        FilterType.Pixel -> PixelFilter()
        FilterType.Blur -> BlurFilter()
    }

    override val localization: Localization = object : Localization {
        override val appName = "Portfolio"
        override val loading = "Loading images..."
        override val repoEmpty = "Repository is empty."
        override val noInternet = "No internet access."
        override val repoInvalid = "List of images in current repository is invalid or empty."
        override val refreshUnavailable = "Cannot refresh images."
        override val loadImageUnavailable = "Cannot load full size image."
        override val lastImage = "This is last image."
        override val firstImage = "This is first image."
        override val picture = "Picture:"
        override val size = "Size:"
        override val pixels = "pixels."
        override val back = "Back"
    }


    override val httpClient: WrappedHttpClient = createWrappedHttpClient()

    override val notification: Notification = object : PopupNotification(localization) {
        override fun showPopUpMessage(text: String) {
            toastState.value = ToastState.Shown(text)
        }
    }
}
