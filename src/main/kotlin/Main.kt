import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay
import kotlin.random.Random
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.vector.DefaultStrokeLineWidth


fun main() = app()

@OptIn(ExperimentalComposeUiApi::class)
fun app() = application {
    val offsetHorizontal = remember { mutableStateOf(0) }
    val offsetVertical = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            offsetHorizontal.value = Random.nextInt(0, 100)
            offsetVertical.value = Random.nextInt(0, 100)
            println("randomize!")
        }
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = 500.dp, height = 500.dp),
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
            val canvasWidth = size.width
            val canvasHeight = size.height
            var xOffset = 0f
//            drawRect(
//                color = Color.DarkGray,
//                topLeft = Offset(canvasWidth / 4 + offsetHorizontal.value, canvasHeight / 4 + offsetVertical.value),
//                size = Size(canvasWidth / 2, canvasHeight / 2)
//            )

            repeat(9) {
                xOffset =+ 100f
                drawLine(
                color = Color.Black,
                start = Offset(xOffset, -20f),
                end = Offset(xOffset, 1000f),
                strokeWidth = 10f
            )

            }

//            drawLine(
//                color = Color.Black,
//                start = Offset(100f, -20f),
//                end = Offset(100f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(200f, -20f),
//                end = Offset(200f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(300f, -20f),
//                end = Offset(300f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(400f, -20f),
//                end = Offset(400f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(500f, -20f),
//                end = Offset(500f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(600f, -20f),
//                end = Offset(600f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(700f, -20f),
//                end = Offset(700f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(800f, -20f),
//                end = Offset(800f, 1000f),
//                strokeWidth = 10f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(900f, -20f),
//                end = Offset(900f, 1000f),
//                strokeWidth = 10f
//            )
        }
    }
}

@Preview
@Composable
fun MessageCard() {
    Text("Hello World")
}