package example.portfolio.view

import androidx.compose.runtime.Composable

@Composable
internal expect fun Tooltip(
    text: String,
    content: @Composable () -> Unit
)
