package example.imageviewer.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
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
fun LinksSection(links: List<Link>) {
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
                Image(
                    painter = painterResourceCached(link.img),
                    contentDescription = link.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(30.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = link.title,
                    style = MaterialTheme.typography.h5
                )
            }
        }

    }
}