package example.portfolio.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/***
 * Creates a display with languages listed next to a visual display of my confidence and experience with them.
 * @param langs A map of my languages to the number to stars to be displayed next to them.
 * @param modifier This modifier will be applied to the top level composable in this composable.
 */
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