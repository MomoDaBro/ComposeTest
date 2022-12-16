import androidx.compose.runtime.*
import androidx.compose.ui.graphics.drawscope.rotate
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Game() {
    val oldPieces = mutableListOf<Pair<Int, Int>>()
    var curPiecePos by remember { mutableStateOf(0 to 0) }

    LaunchedEffect(Unit) {
        println("Started running")
        while (true) {
            delay(50)
            if (curPiecePos.first < NUM_ROW - 1) {
                curPiecePos = curPiecePos.first + 1 to curPiecePos.second
            } else {
                delay(400)
                oldPieces += curPiecePos
                curPiecePos = 0 to Random.nextInt(NUM_COL-3)
            }
        }
    }

    Grid {
        oldPieces.forEach { drawIPiece(it.first, it.second) }
        drawIPiece(curPiecePos.first, curPiecePos.second)
    }
}