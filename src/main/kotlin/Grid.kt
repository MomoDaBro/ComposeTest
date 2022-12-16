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

interface GridScope: DrawScope {
    fun drawCell(row: Int, col: Int, color: Color)
    fun drawJPiece(row: Int = 0, col: Int = 5, color: Color = Orange)
    fun drawLPiece(row: Int = 0, col: Int = 3, color: Color = Color.Blue)
    fun drawIPiece(row: Int = 0, col: Int = 3, color: Color = Color.Cyan)
    fun drawSquare(row: Int = 0, col: Int = 4, color: Color = Color.Yellow)
    fun drawTPiece(row: Int = 0, col: Int = 4, color: Color = Purple)
    fun drawSPiece(row: Int = 0, col: Int = 4, color: Color = Color.Green)
    fun drawZPiece(row: Int = 0, col: Int = 4, color: Color = Color.Red)

}

@Composable
fun Grid(modifier: Modifier = Modifier.fillMaxSize(), block: GridScope.() -> Unit) {
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

            object : GridScope, DrawScope by this {
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

                override fun drawJPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 1, col - 1, color)
                    drawCell(row + 1, col - 2, color)
                }

                override fun drawLPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 1, col + 1, color)
                    drawCell(row + 1, col + 2, color)
                }

                override fun drawIPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row, col + 1, color)
                    drawCell(row, col + 2, color)
                    drawCell(row, col + 3, color)
                }

                override fun drawSquare(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row, col + 1, color)
                    drawCell(row + 1, col + 1, color)
                }

                override fun drawTPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 1, col + 1, color)
                    drawCell(row + 1, col - 1, color)

                }

                override fun drawSPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row, col + 1, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 1, col - 1, color)
                }

                override fun drawZPiece(row: Int, col: Int, color: Color) {
                    drawCell(row, col, color)
                    drawCell(row, col - 1, color)
                    drawCell(row + 1, col, color)
                    drawCell(row + 1, col + 1, color)
                }


            }.block()
        }
    }
}