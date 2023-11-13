import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
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
import kotlin.math.ceil

expect fun createWrappedHttpClient(): WrappedHttpClient

@Composable
internal fun ImageViewerWeb() {
    val toastState = remember { mutableStateOf<ToastState>(ToastState.Hidden) }
    val ioScope: CoroutineScope = rememberCoroutineScope { ioDispatcher }
    val dependencies = remember(ioScope) { getDependencies(ioScope, toastState) }

    Home()
}

@Composable
fun TechStack(
    data: List<String>,
    ui: List<String>,
    other: List<String>,
    modifier: Modifier = Modifier
) {
    @Composable
    fun techDisplay(tech: String) {
        Text(
            text = tech,
            style = MaterialTheme.typography.body1
        )
    }

    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier.width(400.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(4.dp).fillMaxWidth()
        ) {
            Text(
                text = "Tech Stack",
                style = MaterialTheme.typography.h4
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                val groups = mutableListOf(ui, other, data)
                val titles = mutableListOf("UI", "Middle", "Data")
                groups.forEachIndexed { index, group ->
                    Column {
                        Text(text = titles[index], style = MaterialTheme.typography.h5)

                        group.forEach {
                            techDisplay(it)
                        }
                    }
                }
            }
        }
    }
}

internal val subpadding = 12.dp
internal const val oaText =
    "During the last weeks of my Python course at CNM, I struggled to find motivation for my final project. Soon, motivation came courtesy of my classmates and our collective lack of organization in the face of the COVID-19 pandemic. So we talked about our collective struggles and I decided that my project could help us support each other. So I created a collaborative task tracking and organization. Not only did this get me a perfect score on my python final but it also helped my class mates and I stay organized and productive through out the pandemic lockdowns."
internal const val botballText =
    "In the KISS Institute for Practical Robotics competition Bot Ball, my partner and I created robots using provided materials. We then used C to program them to complete a set of objectives designed to maximize points and minimize time and potential failure rate. I led all of the programming for our team and used image recognition to complete objectives with randomized locations."
internal const val descriptionAndObjectivesText =
    "I am a motivated 19 year old software engineer and native android developer with a background in robotics and web-design. I am looking for a company at which I can sharpen my skills, or learn new ones, while delivering interesting and important products to others. Recently I've been focusing a lot on Jetpack Compose and have been following and using it since alpha."
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubSection(
    title: String,
    subtitle: String? = null,
    link: String? = null
) {
    val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
    val modifier = Modifier.apply {
        if (link != null) {
            this.onClick {
                uriHandler.openUri(link)
            }
        }
    }
    Text(
        text = title,
        style = MaterialTheme.typography.h3,
        modifier = modifier
    )

    subtitle?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = subpadding).absoluteOffset(y = (-4).dp)
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
fun BulletList(vararg items: String) {
    Column{
        items.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Card(
                    shape = CircleShape,
                    backgroundColor = MaterialTheme.colors.onSecondary
                ) {}
                Spacer(modifier = Modifier.padding(5.dp))
                Text(it, style = MaterialTheme.typography.h6)
            }
        }
    }
}

@Composable
fun Home() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column {
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
                    text = descriptionAndObjectivesText,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = subpadding)
                )
            }

            item {
                Section("Projects")
            }

            item {
                SubSection(
                    "D&D Helper",
                    "Publicly available open source Android app made with Jetpack Compose",
                    "https://play.google.com/store/apps/details?id=gmail.loganchazdon.dndhelper"
                )
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        val imgs = listOf(
                            "D&DHelper_classes.png",
                            "D&DHelper_spell.png",
                            "D&DHelper_stats.png",
                            "D&DHelper_subclass.png"
                        )
                        imgs.forEach { img ->
                            androidx.compose.foundation.Image(
                                painter = painterResourceCached(img),
                                contentDescription = "D&D Helper screenshot",
                                contentScale = androidx.compose.ui.layout.ContentScale.Fit,
                                modifier = Modifier.height(360.dp)
                            )
                        }

                    }

                    Column {
                        TechStack(
                            data = listOf("SQLite", "Room", "JSON", "GSON"),
                            ui = listOf("Jetpack Compose", "Material Design"),
                            other = listOf("Junit", "Dagger-Hilt", "Coroutines", "Gradle")
                        )

                        LinksSection(
                            imgs = listOf("github-mark.png", "playstore_icon.png"),
                            links = listOf(
                                "https://github.com/Logan-Chazdon/DnDHelper",
                                "https://play.google.com/store/apps/details?id=gmail.loganchazdon.dndhelper"
                            ),
                            titles = listOf("Github", "Google Play")
                        )

                    }
                }
            }

            item {
                SubSection("Bot Ball", "Robotics Competition")
                Text(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    text = botballText
                )
            }


            item {
                SubSection("Organizational Assistant", "Discord bot made with Python")
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        text = oaText
                    )
                    Column {
                        TechStack(
                            ui = listOf("Discord API"),
                            data = listOf("MongoDB", "Regex"),
                            other = listOf("Python")
                        )
                        LinksSection(
                            imgs = listOf("github-mark.png"),
                            links = listOf("https://github.com/Logan-Chaazdon/Organization-Assitant/blob/main/Discord%20bot%200.3.py"),
                            titles = listOf("Github")
                        )
                    }
                }
            }


            item {
                Section("Eduction")
            }

            item {
                SubSection(
                    "Central New Mexico Community College",
                    "Attended from xx/xx/xx to xx/xx/xx as a dual credit student"
                )

                Text(
                    text = "Notable Courses",
                    style = MaterialTheme.typography.h4
                )
                ResponsiveStringGrid("Android Development", "Java", "C++", "C", "Linux", modifier = Modifier.fillMaxWidth(0.5f))
            }

            item {
                SubSection(
                    "Media Arts Collaborative Charter School",
                    "Attended from xx/xx/xx to xx/xx/xx in the programming and design pathway"
                )
                Text(
                    text = "Notable Courses",
                    style = MaterialTheme.typography.h4
                )
                ResponsiveStringGrid("Web Design", "Graphic Design", "Animation", modifier = Modifier.fillMaxWidth(0.5f))
            }

            item {
                SubSection(
                    "Web Design TA",
                    "Assisted students, explained material, and graded websites"
                )
            }

            item {
                SubSection("MACCS Website Update", "Updated school website as a student")
            }

            item {
                SubSection("Howard Zinn Historical Thought Award", "Awarded for excellent writing and Academic discourse")
            }

            item {
                Section("Languages and Technologies")
                Row {
                    LanguageDisplay(
                        mapOf(
                            "Kotlin" to 5,
                            "Java" to 5,
                            "SQL" to 4,
                            "C" to 4,
                            "C++" to 3,
                            "Python" to 2,
                            "C#" to 2
                        ),
                        Modifier.weight(1f).padding(end = 24.dp)
                    )

                    Card(
                        modifier = Modifier.weight(2f),
                        backgroundColor = MaterialTheme.colors.secondary
                    ){
                        ResponsiveStringGrid(
                            "Flask",
                            "MVVM",
                            "MVP",
                            "Jetpack Compose",
                            "Git",
                            "Embedded Software",
                            "CI/CD",
                            "Android SDK",
                            "Asynchronous Software",
                            "Database Design",
                            "Linux",
                            "Virtualization",
                            "Jupyter notebooks",
                            "TensorFlow",
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )
                    }
                }
            }

            item {
                Section("Organization and Clubs")
                SubSection("Nation Honor Society")
                SubSection("Student Government", "Planned events and assisted in setting fund raiser pricing")
                SubSection("Robotics Club", "Founding member at Media Arts Collaborative Charter School")
            }

        }

        val imgs: List<String> = listOf(
            "github-mark.png",
            "linkedin-black-icon.png",
            "email-icon.png"
        )

        val titles = listOf(
            "Github",
            "LinkedIn",
            "loganchazdon@gmail.com"
        )
        val links = listOf(
            "https://github.com/Logan-Chazdon",
            "https://www.linkedin.com/in/logan-chazdon-76940a248",
            "loganchazdon@gmail.com"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primarySurface)
        ) {
            LinksSection(imgs, titles, links)
        }
    }
}

/**
 * Responsively lays out all strings in a grid and left aligns each column.
 * @param titles All strings to be laid out.
 * @param modifier Modifier to be applied to the top level composable.
 */
@Composable
fun ResponsiveStringGrid(vararg titles: String, modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    titles.sortByDescending { it.length }
    var size by remember { mutableStateOf(IntSize.Zero) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.onSizeChanged {
            size = it
        }.padding(8.dp),
    ) {
        val pixels = textMeasurer.measure(titles[0], MaterialTheme.typography.h6).size.width
        val measuredTitles = mutableMapOf<Dp, MutableList<String>>()
        val columns = ceil(size.width.toDouble() / pixels.toDouble())
        val rows = ceil(titles.size / columns)
        titles.forEach {
            var done = false
            val tdp = with(LocalDensity.current) {  textMeasurer.measure(it, MaterialTheme.typography.h6).size.width.toDp() }
            for(entry in measuredTitles) {
                if(tdp <= entry.key && entry.value.size < rows) {
                    measuredTitles[entry.key]!!.add(it)
                    done = true
                    break
                }
            }
            if(!done) {
                measuredTitles[tdp] = mutableListOf(it)
            }
        }

        measuredTitles.forEach { entry ->
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.width(entry.key)) {
                entry.value.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun LanguageDisplay(langs: Map<String, Int>, modifier: Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            Column {
                langs.keys.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            Column {
                langs.values.forEach {
                    Row {
                        for (i in 0 until 5) {
                            Icon(
                                Icons.Default.Star,
                                "",
                                tint = if (i < it) {
                                    MaterialTheme.colors.primaryVariant
                                } else {
                                    MaterialTheme.colors.surface
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LinksSection(imgs: List<String>, titles: List<String>? = null, links: List<String>) {
    Row(
        modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
        imgs.forEachIndexed { index, img ->
            Row(
                modifier = Modifier.clickable { uriHandler.openUri(links[index]) },
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResourceCached(img),
                    contentDescription = titles?.getOrNull(index) ?: links[index],
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit,
                    modifier = Modifier.size(30.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                titles?.get(index)?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5
                    )
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
