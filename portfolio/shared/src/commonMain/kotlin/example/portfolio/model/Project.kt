package example.portfolio.model

class Project(
    val title : String,
    val images: List<String>? = null,
    val subtitle: String? = null,
    val techStack: TechStackData? = null,
    val links: List<Link>? = null,
    val desc: String? = null
)
