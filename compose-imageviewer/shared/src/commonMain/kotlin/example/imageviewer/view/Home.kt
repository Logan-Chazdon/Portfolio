package example.imageviewer.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import example.imageviewer.painterResourceCached


internal val mainPadding = 24.dp
internal val subpadding = 12.dp
internal const val oaText =
    "During the last weeks of my Python course at CNM, I struggled to find motivation for my final project. Soon, motivation came courtesy of my classmates and our collective lack of organization in the face of the COVID-19 pandemic. So we talked about our collective struggles and I decided that my project could help us support each other. So I created a collaborative task tracking and organization. Not only did this get me a perfect score on my python final but it also helped my class mates and I stay organized and productive through out the pandemic lockdowns."
internal const val botballText =
    "In the KISS Institute for Practical Robotics competition Bot Ball, my partner and I created robots using provided materials. We then used C to program them to complete a set of objectives designed to maximize points and minimize time and potential failure rate. I led all of the programming for our team and used image recognition to complete objectives with randomized locations."
internal const val descriptionAndObjectivesText =
    "I am a motivated 19 year old software engineer and native android developer with a background in robotics and web-design. I am looking for a company at which I can sharpen my skills, or learn new ones, while delivering interesting and important products to others. Recently I've been focusing a lot on Jetpack Compose and have been using it since alpha."
internal const val websiteText = "This website is made with jetpack compose multiplatform and can be viewed as a native android application, Ios Application, desktop Application or Website, all with the same codebase."

@OptIn(ExperimentalFoundationApi::class)
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
                        Image(
                            painter = painterResourceCached(img),
                            contentDescription = "D&D Helper screenshot",
                            contentScale = ContentScale.Fit,
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
            SubSection("Bot Ball", "Robotics Competition")
            Text(
                modifier = Modifier.fillMaxWidth(0.6f),
                text = botballText
            )
        }


        item {
            SubSection("Portfolio Website")
            Text(
                text = websiteText,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
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
            ResponsiveStringGrid(
                "Android Development",
                "Java",
                "C++",
                "C",
                "Linux",
                modifier = Modifier.fillMaxWidth(0.5f)
            )
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
            ResponsiveStringGrid(
                "Web Design",
                "Graphic Design",
                "Animation",
                modifier = Modifier.fillMaxWidth(0.5f)
            )
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
            SubSection(
                "Howard Zinn Historical Thought Award",
                "Awarded for excellent writing and Academic discourse"
            )
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
                        "Bash" to 3,
                        "Python" to 2,
                        "C#" to 2
                    ),
                    Modifier.weight(1f).padding(end = 24.dp)
                )

                Card(
                    modifier = Modifier.weight(2f),
                    backgroundColor = MaterialTheme.colors.secondary
                ) {
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

        item {
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
                    .background(color = MaterialTheme.colors.primaryVariant)
                    .fillWidthOfParent(mainPadding)
                    .fillMaxWidth()
            ) {
                LinksSection(imgs, titles, links)
            }
        }
    }
}
