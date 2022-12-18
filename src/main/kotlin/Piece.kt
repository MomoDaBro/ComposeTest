import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

enum class Piece {
    J, L, I, O, T, S, Z
}

context(DrawScope)
fun Piece.draw(row: Int, col: Int, color: Color = this.color) {
    when (this) {
        Piece.J -> {
            drawCell(row, col, color)
            drawCell(row + 1, col, color)
            drawCell(row + 1, col - 1, color)
            drawCell(row + 1, col - 2, color)
        }

        Piece.L -> {
            drawCell(row, col, color)
            drawCell(row + 1, col, color)
            drawCell(row + 1, col + 1, color)
            drawCell(row + 1, col + 2, color)
        }

        Piece.I -> {
            drawCell(row, col, color)
            drawCell(row, col + 1, color)
            drawCell(row, col + 2, color)
            drawCell(row, col + 3, color)
        }

        Piece.O -> {
            drawCell(row, col, color)
            drawCell(row + 1, col, color)
            drawCell(row, col + 1, color)
            drawCell(row + 1, col + 1, color)
        }

        Piece.T -> {
            drawCell(row, col, color)
            drawCell(row + 1, col, color)
            drawCell(row + 1, col + 1, color)
            drawCell(row + 1, col - 1, color)
        }

        Piece.S -> {
            drawCell(row, col, color)
            drawCell(row, col + 1, color)
            drawCell(row + 1, col, color)
            drawCell(row + 1, col - 1, color)
        }

        Piece.Z -> {
            drawCell(row, col, color)
            drawCell(row, col - 1, color)
            drawCell(row + 1, col, color)
            drawCell(row + 1, col + 1, color)
        }
    }

}

val Piece.color:Color get() {
    return when(this){
        Piece.J -> Color.Blue
        Piece.L -> Color.Orange
        Piece.I -> Color.Cyan
        Piece.O -> Color.Yellow
        Piece.T -> Color.Purple
        Piece.S -> Color.Green
        Piece.Z -> Color.Red
    }
}
