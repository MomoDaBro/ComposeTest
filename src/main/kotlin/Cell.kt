import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

context(DrawScope)
fun drawCell(row: Int, col: Int, color: Color) {
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