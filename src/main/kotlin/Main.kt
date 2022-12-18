import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.delay

// Tetris consts
const val CELL_SIZE = 40f
const val NUM_COL = 10
const val NUM_ROW = 20

// UI consts
const val CANVAS_HEIGHT = 1000
const val CANVAS_WIDTH = 1000

// Other
const val CELL_BORDER_SIZE = 8
val Color.Companion.Orange get() = Color(red = 255, green = 165, blue = 0)
val Color.Companion.Purple get() = Color(red = 128, green = 0, blue = 128)
fun main() = app()

@OptIn(ExperimentalComposeUiApi::class)
fun app() = application {
    val shiftDownTimer = remember { mutableStateOf(0) }
    val offsetHorizontal = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            shiftDownTimer.value += 1
        }
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = CANVAS_WIDTH.dp, height = CANVAS_HEIGHT.dp),
        onKeyEvent = {

            if (it.key == Key.DirectionRight) {
                offsetHorizontal.value += 40
            }

            if (it.key == Key.DirectionLeft) {
                offsetHorizontal.value -= 40
            }
            true
        }
    ) {
        Game()
    }

}