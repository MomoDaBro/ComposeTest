import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate

val xGridStart = 50
val yGridStart = 50

interface GridScope {
    fun drawCell(row: Int, col: Int, color: Color)
    fun drawNormalLPiece(row: Int, col: Int, color: Color = Orange)
    fun drawInvertedLPiece(row: Int, col: Int, color: Color = Color.Blue)
    fun drawLine(row: Int, col: Int, color: Color = Color.Cyan)
    fun drawSquare(row: Int, col: Int, color: Color = Color.Yellow)
    fun drawTetris(row: Int, col: Int, color: Color = Purple)
    fun drawNormalZPiece(row: Int, col: Int, color: Color = Color.Green)
    fun drawInvertedZPiece(row: Int, col: Int, color: Color = Color.Red)

}

@Composable
fun Grid(block: GridScope.() -> Unit) {
    Canvas(modifier = Modifier.fillMaxSize()) {
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

            object : GridScope {
                override fun drawCell(row: Int, col: Int, color: Color) {
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

                override fun drawNormalLPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 2, col, color)
                    drawCell(row + 2, col + 1, color)
                }

                override fun drawInvertedLPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 2, col, color)
                    drawCell(row + 2, col - 1, color)
                }

                override fun drawLine(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 2, col, color)
                    drawCell(row + 3, col, color)
                }

                override fun drawSquare(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row, col + 1, color)
                    drawCell(row + 1, col + 1, color)
                }

                override fun drawTetris(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row, col + 1, color)
                    drawCell(row, col + 2, color)
                    drawCell(row + 1, col + 1, color)

                }

                override fun drawNormalZPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row, col + 1, color)
                    drawCell(row - 1, col + 1, color)
                    drawCell(row - 1, col + 2, color)
                }

                override fun drawInvertedZPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row, col - 1, color)
                    drawCell(row + 1, col + 1, color)
                }


            }.block()
        }
    }
}