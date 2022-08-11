import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


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
                offsetHorizontal.value += 10
            }

            if (it.key == Key.DirectionLeft) {
                offsetHorizontal.value -= 10
            }
            if (it.key == Key.DirectionDown) {
                offsetVertical.value += 10
            }
            if (it.key == Key.DirectionUp) {
                offsetVertical.value -= 10
            }
            true
        }
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            val squareSize = 40f
            val numColumns = 10
            val numRows = 20
            val xGridStart = 50
            val yGridStart = 50
            val differenceInSizes = 8

            repeat(numColumns + 1) {
                drawLine(
                    color = Color.Black,
                    start = Offset(xGridStart + squareSize * it, yGridStart.toFloat()),
                    end = Offset(xGridStart + squareSize * it, yGridStart + numRows * squareSize),
                    strokeWidth = 1f
                )
            }

            repeat(numRows + 1) {
                drawLine(
                    color = Color.Black,
                    start = Offset(xGridStart.toFloat(), yGridStart + squareSize * it),
                    end = Offset(xGridStart + squareSize * numColumns, yGridStart + squareSize * it),
                    strokeWidth = 1f
                )
            }


            fun addCyanSquare(row :Int,col :Int) {
                drawRect(
                    color = Color.Black,
                    topLeft = Offset(xGridStart.toFloat()+squareSize*col, yGridStart.toFloat()+squareSize*row),
                    size = Size(squareSize, squareSize)
                )

                drawRect(
                    color = Color.Cyan,
                    topLeft = Offset((xGridStart+differenceInSizes/2f)+squareSize*col, (yGridStart+differenceInSizes/2f)+squareSize*row),
                    size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                )

            }

            addCyanSquare(4,7)
        }
    }
}
