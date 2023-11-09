
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import example.imageviewer.*
import example.imageviewer.core.BitmapFilter
import example.imageviewer.core.FilterType
import example.imageviewer.model.*
import example.imageviewer.model.filtration.BlurFilter
import example.imageviewer.model.filtration.GrayScaleFilter
import example.imageviewer.model.filtration.PixelFilter
import example.imageviewer.utils.ioDispatcher
import example.imageviewer.view.ToastState
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.ExperimentalResourceApi

expect fun createWrappedHttpClient(): WrappedHttpClient

@Composable
internal fun ImageViewerWeb() {
    val toastState = remember { mutableStateOf<ToastState>(ToastState.Hidden) }
    val ioScope: CoroutineScope = rememberCoroutineScope { ioDispatcher }
    val dependencies = remember(ioScope) { getDependencies(ioScope, toastState) }

    Home()
}

internal val subpadding = 8.dp

@Composable
fun SubSection(
    title: String,
    subtitle : String? = null,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h3
    )

    subtitle?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = subpadding).absoluteOffset(y = (-6).dp)
        )
    }
}

@Composable
fun Section(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h2
    )
}

@Composable
fun Home() {
    val state = rememberLazyListState()
    val scope= rememberCoroutineScope()

    LazyColumn(
        userScrollEnabled = true,
        state = state,
        contentPadding = PaddingValues(24.dp),
    ) {
        item {
            Text(
                text = "Logan Chazdon",
                style = MaterialTheme.typography.h1
            )
        }

        item {
            Text(
                text = "This is my description and objectives This is my description and objectivesThis is my description and objectivesThis is my description and objectivesThis is my description and objectivesThis is my description and objectives",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = subpadding)
            )
        }

        item {
            Section("Projects and Experience")
        }

        item {
            SubSection("D&D Helper", "Publicly available open source Android app made with Jetpack Compose")
        }

        item {
            SubSection("Bot Ball", "Robotics Competition")
        }

        item {
            SubSection("Web Design TA", "Assisted students, explained material, graded websites, assisted with syllabus")
        }

        item {
            SubSection("Organization Assistant", "Discord bot made with Python")
        }

        item {
            SubSection("School Website", "Updated official school website")
        }

        item {
            Section("Eduction")
        }

        item {
            SubSection("Central New Mexico Community College", "Attended from xx/xx/xx to xx/xx/xx as a dual credit student")
        }

        item {
            Section("Accounts and Contact information")
        }

        item {
            val imgs : List<String> = listOf(
                "github-mark.png",
                "linkedin-black-icon.png",
                "email-icon.png"
            )

            val titles = listOf(
                "GitHub",
                "LinkedIn",
                "loganchazdon@gmail.com"
            )
            val links = listOf(
                "https://github.com/Logan-Chazdon",
                "https://www.linkedin.com/in/logan-chazdon-76940a248",
                "loganchazdon@gmail.com"
            )

            Column(
                modifier = Modifier.fillMaxWidth(0.3f)
            ) {
                val uriHandler = LocalUriHandler.current
                imgs.forEachIndexed { index, img ->
                    Row(
                        modifier = Modifier.clickable { uriHandler.openUri(links[index]) }
                    ) {
                        Image(
                            painter = painterResourceCached(img),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(50.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(text = titles[index], style= MaterialTheme.typography.h5)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
fun getDependencies(ioScope: CoroutineScope, toastState: MutableState<ToastState>) = object : Dependencies {
    override val pictures: SnapshotStateList<PictureData> = mutableStateListOf(*resourcePictures)
    override val ioScope: CoroutineScope = ioScope
    override fun getFilter(type: FilterType): BitmapFilter = when (type) {
        FilterType.GrayScale -> GrayScaleFilter()
        FilterType.Pixel -> PixelFilter()
        FilterType.Blur -> BlurFilter()
    }

    override val localization: Localization = object : Localization {
        override val appName = "ImageViewer"
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

    private fun ContentRepository<ImageBitmap>.cacheByUrlAdapter(): ContentRepository<ImageBitmap> {
        val original = this
        return object : ContentRepository<ImageBitmap> {
            val cache = mutableMapOf<String, ImageBitmap>()
            override suspend fun loadContent(url: String): ImageBitmap {
                return cache.getOrPut(url) {
                    original.loadContent(url)
                }
            }
        }
    }

    override val imageRepository: ContentRepository<ImageBitmap> =
        createNetworkRepository(httpClient)
            .adapter { it.toImageBitmap() }
            .cacheByUrlAdapter()

    override val notification: Notification = object : PopupNotification(localization) {
        override fun showPopUpMessage(text: String) {
            toastState.value = ToastState.Shown(text)
        }
    }
}
