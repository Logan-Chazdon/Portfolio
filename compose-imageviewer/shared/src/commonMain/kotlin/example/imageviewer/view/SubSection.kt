package example.imageviewer.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/***
 * Creates a smaller title used to denote an individual member of a greater category.
 * @param title The main text to display.
 * @param subtitle Smaller text displayed under the title to clarify or add information.
 * @param link Where to navigated too if the title is pressed.
 */
@Composable
fun SubSection(
    title: String,
    subtitle: String? = null,
    link: String? = null
) {
    val uriHandler = androidx.compose.ui.platform.LocalUriHandler.current
    val modifier = Modifier.apply {
        if (link != null) {
            this.clickable {
                uriHandler.openUri(link)
            }
        }
    }
    Text(
        text = title,
        style = MaterialTheme.typography.h3,
        modifier = modifier
    )

    subtitle?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = subpadding).absoluteOffset(y = (-4).dp)
        )
    }
}