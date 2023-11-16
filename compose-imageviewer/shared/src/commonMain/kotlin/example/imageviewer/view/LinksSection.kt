package example.imageviewer.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import example.imageviewer.model.Link
import example.imageviewer.painterResourceCached


/***
 * Renders a display with Icons to show where the project can be viewed and link to said locations.
 * @param imgs The icon to display. Ex githubLogo
 * @param titles The title of the location. Ex github
 * @param links Where click will navigate the browser to
 */
@Composable
fun LinksSection(links: List<Link>, drawTitles: Boolean = true, size: Dp = 30.dp) {
    Row(
        modifier = Modifier.padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val uriHandler = LocalUriHandler.current
        links.forEachIndexed { index, link ->
            Row(
                modifier = Modifier.clickable { uriHandler.openUri(link.link) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResourceCached(link.img),
                    contentDescription = link.title,
                    modifier = Modifier.size(size),
                    tint = MaterialTheme.colorScheme.primary
                )

                if(drawTitles) {
                    Spacer(modifier = Modifier.width(size / 6))

                    Text(
                        text = link.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}