package example.imageviewer.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

/***
 * Creates a title denoting a new section of the page.
 */
@Composable
fun Section(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h2
    )
}
