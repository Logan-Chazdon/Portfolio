package example.imageviewer.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/***
 * Creates a display showing the technologies used to make a project
 */
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