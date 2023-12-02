package example.portfolio.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import example.portfolio.model.TechStackData
import org.jetbrains.skia.skottie.Logger

/***
 * Creates a display showing the technologies used to make a project
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TechStack(
    data: TechStackData,
    modifier: Modifier = Modifier
) {
    @Composable
    fun techDisplay(tech: String) {
        Text(
            text = tech,
            style = MaterialTheme.typography.body1
        )
    }
    val width = LocalWindowInfo.current.containerSize.width


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
            VariableOrientationView(
                isVertical = width.dp < 1750.dp,
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                val groups = mutableListOf(data.ui, data.middle, data.data)
                val titles = mutableListOf("UI", "Middle", "Data")
                groups.forEachIndexed { index, group ->
                    Column(
                        modifier = Modifier.padding(top = 6.dp)
                    ) {
                        Text(text = titles[index], style = MaterialTheme.typography.h5)

                        group?.forEach {
                            techDisplay(it)
                        }
                    }
                }
            }
        }
    }
}