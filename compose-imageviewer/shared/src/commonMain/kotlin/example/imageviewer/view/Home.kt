package example.imageviewer.view

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import example.imageviewer.model.Portfolio
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

internal val mainPadding = 24.dp
internal val subpadding = 12.dp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
fun Home() {
    val windowSize = LocalWindowInfo.current.containerSize
    val windowHeight = windowSize.height.dp
    var i = 0
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant).fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(5.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Logan Chazdon",
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = "Software Engineer",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    LinksSection(Portfolio.links, false, 45.dp)
                }
            }
        }
    ) { paddingValues ->
        val state = rememberLazyListState()

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            state = state
        ) {
            item(i) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = mainPadding, start = mainPadding, end = mainPadding, bottom = mainPadding * 4)
                ) {
                    val text = buildAnnotatedString {
                        append("Hello, my name is ")

                        pushStyle(SpanStyle(color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold))
                        append("Logan Chazdon ")
                        pop()

                        append("and I am an ")

                        pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary))
                        append("Android Engineer ")
                        pop()

                        append("specializing in ")

                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.inversePrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        append("Jetpack Compose")
                        pop()
                        append(". ")

                        append("I have a experience with robotics, web design, and Machine Learning. I am currently searching for a company where I can learn new skills and sharpen the ones I already have. I have been using ")

                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.inversePrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        append("Jetpack Compose")
                        pop()
                        append(" ")

                        append("since alpha and have used it on multiple projects including this website.")

                        append(" Take a look around at some of my qualifications and previous projects.")
                        toAnnotatedString()
                    }

                    Text(text)
                }
            }


            Section("Projects", windowHeight)

            Portfolio.projects.forEach {
                with(it) {
                    stickyHeader {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.surface)
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.displaySmall,
                                modifier = Modifier.padding(start = mainPadding)
                            )

                            subtitle?.let { text ->
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = mainPadding, start = mainPadding)
                                )
                            }
                        }
                    }

                    item(++i) {

                        Column(
                            modifier = Modifier
                                .heightIn(min = windowHeight / 2)
                                .padding(mainPadding),
                            verticalArrangement = Arrangement.spacedBy(mainPadding)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.weight(3f)) {
                                    desc?.let { desc ->
                                        Text(
                                            text = desc,
                                        )
                                    }
                                    Row {
                                        images?.forEach { res ->
                                            Image(
                                                painter = painterResource(res),
                                                contentDescription = "$title image",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.weight(1f)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.weight(0.5f))
                                Column(modifier = Modifier.weight(1f)) {
                                    techStack?.let { stack ->
                                        TechStack(stack, modifier = Modifier.weight(1f))
                                    }

                                    links?.let { links -> LinksSection(links, size = 45.dp) }
                                }
                            }
                        }
                    }
                }
            }

            Section("Education", windowHeight)

            Portfolio.education.forEach {
                with(it) {
                    stickyHeader {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.surface)
                        ) {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.displaySmall,
                                modifier = Modifier.padding(start = mainPadding)
                            )

                            Text(text = desc, modifier = Modifier.padding(start = mainPadding))
                        }
                    }

                    item(++i) {
                        Column(
                            modifier = Modifier
                                .heightIn(min = windowHeight / 2)
                                .padding(mainPadding),
                            verticalArrangement = Arrangement.spacedBy(mainPadding)
                        ) {
                            Text(
                                text = "Notable Courses",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            ResponsiveStringGrid(courses, modifier = Modifier.fillMaxWidth(0.4f))

                            Column(
                                verticalArrangement = Arrangement.spacedBy(34.dp)
                            ) {
                                items.forEach {
                                    Column {
                                        Text(
                                            text = it.title,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                        it.subtitle?.let { subtitle ->
                                            Text(
                                                text = subtitle,
                                                style = MaterialTheme.typography.titleSmall
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            stickyHeader {
                Text(
                    text = "Languages and Technologies",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(start = mainPadding)
                )
            }

            item(++i) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(mainPadding),
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    LanguageDisplay(
                        langs = Portfolio.languages,
                        modifier = Modifier.weight(1f)
                    )

                    ResponsiveStringGrid(
                        Portfolio.techs,
                        modifier = Modifier.weight(3f)
                    )
                }
            }

            stickyHeader {
                Text(
                    text = "Organizations",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(start = mainPadding)
                )
            }

            items(Portfolio.orgs, { ++i }) {
                with(it) {
                    Column(
                        modifier = Modifier.padding(start = mainPadding, bottom = mainPadding)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        subtitle?.let { subtitle ->
                            Text(
                                text = subtitle,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun animateScrollPosition(
    listState: LazyListState,
    animationSpec: AnimationSpec<Float> = spring()
): Float {
    val layoutInfo = listState.layoutInfo
    val totalItems = layoutInfo.totalItemsCount
    val firstVisibleItem = layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0
    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

    val visibleItemsCount = if (totalItems > 0) lastVisibleItem - firstVisibleItem + 1 else 1

    val visibleItemsHeight = layoutInfo.visibleItemsInfo
        .take(visibleItemsCount)
        .sumBy { it.size }

    val offset = listState.firstVisibleItemScrollOffset
    val scrollPercentage = offset / visibleItemsHeight.toFloat()

    return animateFloatAsState(
        targetValue = scrollPercentage,
        animationSpec = animationSpec
    ).value
}