package example.portfolio.view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import example.portfolio.model.ScalableState
import example.portfolio.model.addDragAmount
import example.portfolio.model.addScale
import example.portfolio.model.setScale

expect fun Modifier.addUserInput(state: ScalableState): Modifier

fun Modifier.addTouchUserInput(state: ScalableState): Modifier =
    pointerInput(Unit) {
        detectTransformGestures { _, pan, zoom, _ ->
            state.addDragAmount(pan)
            state.addScale(zoom - 1f)
        }
    }.pointerInput(Unit) {
        detectTapGestures(
            onDoubleTap = { state.setScale(1f) }
        )
    }
