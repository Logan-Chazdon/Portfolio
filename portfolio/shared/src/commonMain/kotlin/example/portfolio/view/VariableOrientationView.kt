package example.portfolio.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun VariableOrientationView(
    isVertical: Boolean,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceAround,
    verticalAlignment : Alignment.Vertical? = null,
    horizontalAlignment : Alignment.Horizontal? = null,
    modifier : Modifier,
    content: @Composable () -> Unit,
) {
    if(isVertical) {
        Column (
            modifier = modifier,
            verticalArrangement =  arrangement,
            horizontalAlignment = horizontalAlignment ?: Alignment.CenterHorizontally
        ) {
            content.invoke()
        }
    } else {
        Row (
            modifier = modifier,
            horizontalArrangement =arrangement,
            verticalAlignment = verticalAlignment ?: Alignment.CenterVertically
        ) {
            content.invoke()
        }
    }
}