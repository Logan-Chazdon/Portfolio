package example.portfolio.view

import androidx.compose.ui.Modifier
import example.portfolio.model.ScalableState

actual fun Modifier.addUserInput(state: ScalableState): Modifier =
    addTouchUserInput(state)
