package example.imageviewer.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

/**
 * Responsively lays out all strings in a grid and left aligns each column.
 * @param titles All strings to be laid out.
 * @param modifier Modifier to be applied to the top level composable.
 */
@Composable
fun ResponsiveStringGrid(titles: MutableList<String>, modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    titles.sortByDescending { it.length }
    var size by remember { mutableStateOf(IntSize.Zero) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.onSizeChanged {
            size = it
        }.padding(8.dp),
    ) {
        val pixels = textMeasurer.measure(titles[0], MaterialTheme.typography.h6).size.width
        val measuredTitles = mutableMapOf<Dp, MutableList<String>>()
        val columns = ceil(size.width.toDouble() / pixels.toDouble())
        val rows = ceil(titles.size / columns)
        titles.forEach {
            var done = false
            val tdp =
                with(LocalDensity.current) { textMeasurer.measure(it, MaterialTheme.typography.h6).size.width.toDp() }
            for (entry in measuredTitles) {
                if (tdp <= entry.key && entry.value.size < rows) {
                    measuredTitles[entry.key]!!.add(it)
                    done = true
                    break
                }
            }
            if (!done) {
                measuredTitles[tdp] = mutableListOf(it)
            }
        }

        measuredTitles.forEach { entry ->
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.width(entry.key)) {
                entry.value.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}