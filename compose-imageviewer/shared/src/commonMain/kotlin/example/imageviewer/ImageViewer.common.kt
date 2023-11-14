package example.imageviewer

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import example.imageviewer.model.HomePage
import example.imageviewer.model.Page
import example.imageviewer.view.Home
import example.imageviewer.view.NavigationStack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

enum class ExternalImageViewerEvent {
    Foward,
    Back
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ImageViewerCommon(
    externalEvents: Flow<ExternalImageViewerEvent> = emptyFlow()
) {
    val rootHomePage = HomePage()
    val navigationStack = remember { NavigationStack<Page>(rootHomePage) }

    Surface(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(targetState = navigationStack.lastWithIndex(), transitionSpec = {
            val previousIdx = initialState.index
            val currentIdx = targetState.index
            val multiplier = if (previousIdx < currentIdx) 1 else -1
            fadeIn() with fadeOut(tween(durationMillis = 500, 500))
            /*
            if (initialState.value is HomePage && targetState.value is MemoryPage) {
                fadeIn() with fadeOut(tween(durationMillis = 500, 500))
            } else if (initialState.value is MemoryPage && targetState.value is GalleryPage) {
                fadeIn() with fadeOut(tween(delayMillis = 150))
            } else {
                slideInHorizontally { w -> multiplier * w } with
                        slideOutHorizontally { w -> multiplier * -1 * w }
            } */
        }) { (index, page) ->
            when (page) {
                is HomePage -> {
                    Home()
                }
            }
        }
    }
}
