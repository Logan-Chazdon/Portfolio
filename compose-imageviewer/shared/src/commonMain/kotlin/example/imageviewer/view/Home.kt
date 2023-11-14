package example.imageviewer.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import example.imageviewer.model.Portfolio
import example.imageviewer.model.Portfolio.education
import example.imageviewer.model.Portfolio.languages
import example.imageviewer.model.Portfolio.orgs
import example.imageviewer.model.Portfolio.projects
import example.imageviewer.model.Portfolio.techs
import example.imageviewer.model.Portfolio.websiteText
import example.imageviewer.painterResourceCached


internal val mainPadding = 24.dp
internal val subpadding = 12.dp

@Composable
fun Home() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LazyColumn(
        userScrollEnabled = true,
        state = state,
        contentPadding = PaddingValues(top = mainPadding, start = mainPadding, end = mainPadding, bottom = 0.dp)
    ) {
        item {
            Text(
                text = "Logan Chazdon",
                style = MaterialTheme.typography.h1
            )
        }

        item {
            Text(
                text = websiteText,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = subpadding)
            )
        }

        item {
            Section("Projects")
        }

        items(projects) {
            with(it) {
                SubSection(
                    title = title,
                    subtitle = subtitle,
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        desc?.let {
                            Text(
                                modifier = Modifier.fillMaxWidth(0.6f),
                                text = it,
                                style = MaterialTheme.typography.body1
                            )
                        }

                        Row {
                            images?.forEach { img ->
                                Image(
                                    painter = painterResourceCached(img),
                                    contentDescription = "$title image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.height(360.dp)
                                )
                            }
                        }
                    }

                    Column {
                        techStack?.let { data ->
                            TechStack(
                                data
                            )
                        }

                        links?.let { data ->
                            LinksSection(
                                data
                            )
                        }
                    }
                }
            }
        }

        item {
            Section("Eduction")
        }

        items(education) {
            with(it) {
                SubSection(
                    title = name,
                    subtitle = desc
                )

                Text(
                    text = "Notable Courses",
                    style = MaterialTheme.typography.h4
                )

                ResponsiveStringGrid(
                    courses,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )

                items.forEach {item ->
                    SubSection(
                        title = item.title,
                        subtitle= item.subtitle
                    )
                }
            }
        }

        item {
            Section("Languages and Technologies")
            Row {
                LanguageDisplay(
                    languages,
                    Modifier.weight(1f).padding(end = 24.dp)
                )

                Card(
                    modifier = Modifier.weight(2f),
                    backgroundColor = MaterialTheme.colors.secondary
                ) {
                    ResponsiveStringGrid(
                        techs,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                }
            }
        }

        item {
            Section("Organization and Clubs")
        }

        items(orgs) {
            with(it) {
                SubSection(
                    title,
                    subtitle
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primaryVariant)
                    .fillWidthOfParent(mainPadding)
                    .fillMaxWidth()
            ) {
                LinksSection(
                    Portfolio.links
                )
            }
        }
    }
}
