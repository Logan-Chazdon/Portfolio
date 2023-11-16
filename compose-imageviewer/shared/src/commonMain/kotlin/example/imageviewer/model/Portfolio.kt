package example.imageviewer.model

/** Stores all data for the portfolio.
 */
object Portfolio {
    private const val oaText =
        "During the last weeks of my Python course at CNM, I struggled to find motivation for my final project. Soon, motivation came courtesy of my classmates and our collective lack of organization in the face of the COVID-19 pandemic. So we talked about our collective struggles and I decided that my project could help us support each other. So I created a collaborative task tracking and organization. Not only did this get me a perfect score on my python final but it also helped my class mates and I stay organized and productive through out the pandemic lockdowns."
    private const val botballText =
        "In the KISS Institute for Practical Robotics competition Bot Ball, my partner and I created robots using provided materials. We then used C to program them to complete a set of objectives designed to maximize points and minimize time and potential failure rate. I led all of the programming for our team and used image recognition to complete objectives with randomized locations."
    private const val websiteText =
        "This website is made with jetpack compose multiplatform and can be viewed as a native android application, Ios Application, desktop Application or Website, all with the same codebase."

    internal const val descriptionAndObjectivesText =
        "I am a motivated 19 year old software engineer and native android developer with a background in robotics and web-design. I am looking for a company at which I can sharpen my skills, or learn new ones, while delivering interesting and important products to others. Recently I've been focusing a lot on Jetpack Compose and have been using it since alpha."

    val projects = listOf(
        Project(
            title = "D&D Helper",
            subtitle = "Publicly available open source Android app made with Jetpack Compose",
            images = listOf(
                "D&DHelper_classes.png",
                "D&DHelper_spell.png",
                "D&DHelper_stats.png",
                "D&DHelper_subclass.png"
            ),
            techStack = TechStackData(
                data = listOf("SQLite", "Room", "JSON", "GSON"),
                ui = listOf("Jetpack Compose", "Material Design"),
                middle = listOf("Junit", "Dagger-Hilt", "Coroutines", "Gradle")
            ),
            links = listOf(
                Link(
                    img = "github-mark.png",
                    link = "https://github.com/Logan-Chazdon/DnDHelper",
                    title = "Github"
                ),
                Link(
                    img = "playstore_icon.png",
                    link = "https://play.google.com/store/apps/details?id=gmail.loganchazdon.dndhelper",
                    title = "Google Play"
                )
            )
        ),
        Project(
            title = "Organizational Assistant",
            subtitle = "Discord bot made with Python",
            desc = oaText,
            techStack = TechStackData(
                ui = listOf("Discord API"),
                data = listOf("MongoDB", "Regex"),
                middle = listOf("Python")
            ),
            links = listOf(
                Link(
                    img = "github-mark.png",
                    link = "https://github.com/Logan-Chaazdon/Organization-Assitant/blob/main/Discord%20bot%200.3.py",
                    title = "Github"
                )
            )
        ),
        Project(
            title = "Bot Ball",
            subtitle = "Robotics Competition",
            desc = botballText
        ),
        Project(
            title = "Portfolio Website",
            desc = websiteText
        )
    )

    val education = listOf(
        School(
            name = "Central New Mexico Community College",
            desc = "Attended from xx/xx/xx to xx/xx/xx as a dual credit student",
            courses = mutableListOf(
                "Android Development",
                "Java",
                "C++",
                "C",
                "Linux",
            ),
            items = listOf()
        ),
        School(
            name = "Media Arts Collaborative Charter School",
            desc = "Attended from xx/xx/xx to xx/xx/xx in the programming and design pathway",
            courses = mutableListOf(
                "Web Design",
                "Graphic Design",
                "Animation",
            ),
            items = listOf(
                EducationItem(
                    title = "Web Design TA",
                    subtitle = "Assisted students, explained material, and graded websites"
                ),
                EducationItem(
                    title = "MACCS Website Update",
                    subtitle = "Updated school website as a student"
                ),
                EducationItem(
                    title = "Howard Zinn Historical Thought Award",
                    subtitle = "Awarded for excellent writing and Academic discourse"
                )
            )
        )
    )

    val languages = mapOf(
        "Kotlin" to 5,
        "Java" to 5,
        "SQL" to 4,
        "C" to 4,
        "C++" to 3,
        "Bash" to 3,
        "Python" to 2,
        "C#" to 2
    )

    val techs = mutableListOf(
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
    )

    val orgs = listOf(
        EducationItem(
            title ="National Honor Society"
        ),
        EducationItem(
            title = "Student Government",
            subtitle = "Planned events and assisted in setting fund raiser pricing"
        ),
        EducationItem(
            title = "Robotics Club",
            subtitle = "Founding member at Media Arts Collaborative Charter School"
        )
    )

    val links = listOf(
        Link(
            img = "github-mark.png",
            title = "Github",
            link = "https://github.com/Logan-Chazdon"
        ),
        Link(
            img = "linkedin-black-icon.png",
            title = "LinkedIn",
            link = "https://www.linkedin.com/in/logan-chazdon-76940a248"
        ),
        Link(
            img = "email-icon.png",
            title = "loganchazdon@gmail.com",
            link  = "loganchazdon@gmail.com"
        )
    )
}

