import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate

val xGridStart = 50
val yGridStart = 50

@Composable
fun Grid(modifier: Modifier = Modifier.fillMaxSize(), block: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) {
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
            block()
        }
    }
}