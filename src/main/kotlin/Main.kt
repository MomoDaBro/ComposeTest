import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
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
            val squareSize = 40f
            val numColumns = 10
            val numRows = 20
            val xGridStart = 50
            val yGridStart = 50
            val differenceInSizes = 8
            val orange = Color(red =0xFF, green = 0xFF, blue = 0xFF)

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

            fun addYellowSquare(row: Int, col: Int) {
                drawRect(
                    color = Color.Black,
                    topLeft = Offset(
                        xGridStart.toFloat() + squareSize * col + offsetHorizontal.value,
                        yGridStart.toFloat() + squareSize * row + offsetVertical.value
                    ),
                    size = Size(squareSize, squareSize)
                )

                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(
                        (xGridStart + differenceInSizes / 2f) + squareSize * col + offsetHorizontal.value,
                        (yGridStart + differenceInSizes / 2f) + squareSize * row + offsetVertical.value
                    ),
                    size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                )

                drawRect(
                    color = Color.Black,
                    topLeft = Offset(
                        xGridStart.toFloat() + (squareSize * col) + squareSize + offsetHorizontal.value,
                        yGridStart.toFloat() + (squareSize * row) + offsetVertical.value
                    ),
                    size = Size(squareSize, squareSize)
                )

                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(
                        (xGridStart + differenceInSizes / 2f) + (squareSize * col) + squareSize + offsetHorizontal.value,
                        (yGridStart + differenceInSizes / 2f) + (squareSize * row) + offsetVertical.value
                    ),
                    size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                )

                drawRect(
                    color = Color.Black,
                    topLeft = Offset(
                        xGridStart.toFloat() + (squareSize * col) + offsetHorizontal.value,
                        yGridStart.toFloat() + (squareSize * row) + squareSize + offsetVertical.value
                    ),
                    size = Size(squareSize, squareSize)
                )

                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(
                        (xGridStart + differenceInSizes / 2f) + (squareSize * col) + offsetHorizontal.value,
                        (yGridStart + differenceInSizes / 2f) + (squareSize * row) + squareSize + offsetVertical.value
                    ),
                    size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                )

                drawRect(
                    color = Color.Black,
                    topLeft = Offset(
                        xGridStart.toFloat() + (squareSize * col) + squareSize + offsetHorizontal.value,
                        yGridStart.toFloat() + (squareSize * row) + squareSize + offsetVertical.value
                    ),
                    size = Size(squareSize, squareSize)
                )

                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(
                        (xGridStart + differenceInSizes / 2f) + (squareSize * col) + squareSize + offsetHorizontal.value,
                        (yGridStart + differenceInSizes / 2f) + (squareSize * row) + squareSize + offsetVertical.value
                    ),
                    size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                )

            }

            fun addCyanLine(row: Int, col: Int) {

                repeat(4) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            xGridStart.toFloat() + squareSize * col + offsetHorizontal.value,
                            yGridStart.toFloat() + squareSize * row + (squareSize * it) + offsetVertical.value
                        ),
                        size = Size(squareSize, squareSize)
                    )
                }

                repeat(4) {
                    drawRect(
                        color = Color.Cyan,
                        topLeft = Offset(
                            (xGridStart + differenceInSizes / 2f) + squareSize * col + offsetHorizontal.value,
                            (yGridStart.toFloat() + differenceInSizes / 2f) + squareSize * row + (squareSize * it) + offsetVertical.value
                        ),
                        size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                    )
                }
            }

            fun addOrange(row: Int, col : Int){

                repeat(3) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            xGridStart.toFloat() + squareSize * col + offsetHorizontal.value,
                            yGridStart.toFloat() + squareSize * row + (squareSize * it) + offsetVertical.value
                        ),
                        size = Size(squareSize, squareSize)
                    )
                }

                repeat(3) {
                    drawRect(
                        color = orange,
                        topLeft = Offset(
                            (xGridStart + differenceInSizes / 2f) + squareSize * col + offsetHorizontal.value,
                            (yGridStart.toFloat() + differenceInSizes / 2f) + squareSize * row + (squareSize * it) + offsetVertical.value
                        ),
                        size = Size(squareSize - differenceInSizes, squareSize - differenceInSizes)
                    )
                }

            }

            //addYellowSquare(5, 3)
            //addCyanLine(11, 3)
            addOrange(1,1)

        }
    }
}
