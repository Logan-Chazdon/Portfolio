package example.imageviewer.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(mainPadding)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(36.dp)
                    ) {
                        Text(
                            text = "Hi, I'm Logan Chazdon a native Android engineer specializing in Jetpack Compose.",
                        )
                        Text(
                            text = Portfolio.descriptionAndObjectivesText
                        )
                    }
                }
            }

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

                    item {
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

                    item {
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

            item {
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

            items(Portfolio.orgs) {
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
