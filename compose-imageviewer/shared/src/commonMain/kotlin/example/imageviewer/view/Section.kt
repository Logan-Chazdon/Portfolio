package example.imageviewer.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/***
 * Creates a title denoting a new section of the page.
 */

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.Section(title: String, windowHeight: Dp) {
    stickyHeader {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(windowHeight / 3)
                .fillWidthOfParent(mainPadding)
                .fillMaxWidth()
                .background(androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant)
        ) {
            androidx.compose.material3.Text(
                text = title,
                style = androidx.compose.material3.MaterialTheme.typography.displayLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            )
        }

        Spacer(modifier = Modifier.height(mainPadding))
    }
}
