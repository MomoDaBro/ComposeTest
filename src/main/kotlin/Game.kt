import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Game() {
    val oldPieces = mutableListOf<Pair<Int, Int>>()
    var curPiecePos by remember { mutableStateOf(0 to 0) }

    LaunchedEffect(Unit) {
        println("Started running")
        while (true) {
            delay(10)
            if (curPiecePos.first < NUM_ROW - 4) {
                curPiecePos = curPiecePos.first + 1 to curPiecePos.second
            } else {
                delay(400)
                oldPieces += curPiecePos
                curPiecePos = 0 to Random.nextInt(NUM_COL)
            }
        }
    }

    Grid {
        oldPieces.forEach { drawLine(it.first, it.second) }
        drawLine(curPiecePos.first, curPiecePos.second)
    }
}