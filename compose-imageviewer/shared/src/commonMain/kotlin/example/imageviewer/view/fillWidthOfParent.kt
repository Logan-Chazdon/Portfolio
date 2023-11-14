package example.imageviewer.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

/***
 * Allows a composable to take up the space of its parents ignoring padding.
 * @param parentPadding This padding of its parent
 */
@Composable
fun Modifier.fillWidthOfParent(parentPadding: Dp) = this.then(
    layout { measurable, constraints ->
        // This is to force layout to go beyond the borders of its parent
        val placeable = measurable.measure(
            constraints.copy(
                maxWidth = constraints.maxWidth + 2 * parentPadding.roundToPx(),
            ),
        )
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    },
)