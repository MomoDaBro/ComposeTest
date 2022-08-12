import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

// Tetris consts
const val CELL_SIZE = 40f
const val NUM_COL = 10
const val NUM_ROW = 20

// Other
const val CELL_BORDER_SIZE = 8

fun main() = app()

@OptIn(ExperimentalComposeUiApi::class)
fun app() = application {
    val offsetHorizontal = remember { mutableStateOf(0) }
    val offsetVertical = remember { mutableStateOf(0) }
    val canvasHeight = 1000
    val canvasWidth = 1000

//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(1000)
//            offsetHorizontal.value = Random.nextInt(0, 100)
//            offsetVertical.value = Random.nextInt(0, 100)
//            println("randomize!")
//        }
//    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = canvasWidth.dp, height = canvasHeight.dp),
        onKeyEvent = {

            if (it.key == Key.DirectionRight) {
                offsetHorizontal.value += 40
            }

            if (it.key == Key.DirectionLeft) {
                offsetHorizontal.value -= 40
            }
            if (it.key == Key.DirectionDown) {
                offsetVertical.value += 40
            }
            if (it.key == Key.DirectionUp) {
                offsetVertical.value -= 40
            }
            true
        }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            val xGridStart = 50
            val yGridStart = 50

            translate(xGridStart.toFloat(), yGridStart.toFloat()) {
                repeat(NUM_COL + 1) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(CELL_SIZE * it, 0f),
                        end = Offset(CELL_SIZE * it, NUM_ROW * CELL_SIZE),
                        strokeWidth = 1f
                    )
                }

                repeat(NUM_ROW + 1) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, CELL_SIZE * it),
                        end = Offset(CELL_SIZE * NUM_COL, CELL_SIZE * it),
                        strokeWidth = 1f
                    )
                }
//                drawYellowSquare(1, 2)
            }

            fun addCyanLine(row: Int, col: Int) {

                repeat(4) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            xGridStart.toFloat() + CELL_SIZE * col + offsetHorizontal.value,
                            yGridStart.toFloat() + CELL_SIZE * row + (CELL_SIZE * it) + offsetVertical.value
                        ),
                        size = Size(CELL_SIZE, CELL_SIZE)
                    )
                }

                repeat(4) {
                    drawRect(
                        color = Color.Cyan,
                        topLeft = Offset(
                            (xGridStart + CELL_BORDER_SIZE / 2f) + CELL_SIZE * col + offsetHorizontal.value,
                            (yGridStart.toFloat() + CELL_BORDER_SIZE / 2f) + CELL_SIZE * row + (CELL_SIZE * it) + offsetVertical.value
                        ),
                        size = Size(CELL_SIZE - CELL_BORDER_SIZE, CELL_SIZE - CELL_BORDER_SIZE)
                    )
                }
            }

            fun addOrange(row: Int, col: Int) {

                repeat(3) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            xGridStart.toFloat() + CELL_SIZE * col + offsetHorizontal.value,
                            yGridStart.toFloat() + CELL_SIZE * row + (CELL_SIZE * it) + offsetVertical.value
                        ),
                        size = Size(CELL_SIZE, CELL_SIZE)
                    )
                }

                repeat(3) {
                    drawRect(
                        color = Color(red = 255, green = 165, blue = 0),
                        topLeft = Offset(
                            (xGridStart + CELL_BORDER_SIZE / 2f) + CELL_SIZE * col + offsetHorizontal.value,
                            (yGridStart.toFloat() + CELL_BORDER_SIZE / 2f) + CELL_SIZE * row + (CELL_SIZE * it) + offsetVertical.value
                        ),
                        size = Size(CELL_SIZE - CELL_BORDER_SIZE, CELL_SIZE - CELL_BORDER_SIZE)
                    )
                }

            }

//            addYellowSquare(5, 3)
//            addCyanLine(11, 3)
//            addOrange(1,1)
        }
    }
}

fun DrawScope.drawYellowSquare(row : Int, col: Int, color : Color = Color.Yellow){
    drawCell(row,col, color)
    drawCell(row + 1,col,color)
    drawCell(row,col + 1,color)
    drawCell(row + 1,col + 1,color)
}

fun DrawScope.drawCyanLine(row: Int, col: Int){

}

fun DrawScope.drawCell(row: Int, col: Int, color: Color) {
    drawRect(
        color = Color.Black,
        topLeft = Offset(CELL_SIZE * col, CELL_SIZE * row),
        size = Size(CELL_SIZE, CELL_SIZE)
    )

    drawRect(
        color = color,
        topLeft = Offset(
            (CELL_BORDER_SIZE / 2f) + CELL_SIZE * col,
            (CELL_BORDER_SIZE / 2f) + CELL_SIZE * row
        ),
        size = Size(CELL_SIZE - CELL_BORDER_SIZE, CELL_SIZE - CELL_BORDER_SIZE)
    )
}