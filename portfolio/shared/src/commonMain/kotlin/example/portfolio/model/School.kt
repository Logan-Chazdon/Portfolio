package example.portfolio.model

class School(
    val name: String,
    val desc: String,
    val courses: MutableList<String>,
    val items: List<EducationItem>
)