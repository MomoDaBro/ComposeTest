import androidx.compose.foundation.focusable
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import kotlinx.coroutines.delay
import kotlin.random.Random


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Game(focusRequester: FocusRequester) {
    val oldPieces = mutableListOf<Pair<Int, Int>>()
    var curPiecePos by remember { mutableStateOf(0 to 0) }


    LaunchedEffect(Unit) {
        println("Started running")
        while (true) {
            delay(400) //delay for moving piece down
            if (curPiecePos.first < NUM_ROW - 1) {
                curPiecePos = curPiecePos.first + 1 to curPiecePos.second
            } else {
                delay(800) //delay for spawning another piece
                oldPieces += curPiecePos
                curPiecePos = 0 to Random.nextInt(NUM_COL - 3)
            }
        }
    }

    Grid(modifier = Modifier.focusRequester(focusRequester).focusable()
        .onKeyEvent {
            if (it.type == KeyEventType.KeyDown) {
                when (it.key) {
                    Key.DirectionRight -> curPiecePos = curPiecePos.first to curPiecePos.second + 1
                    Key.DirectionLeft -> curPiecePos = curPiecePos.first to curPiecePos.second - 1
                }
            }
            true
        }) {

        focusRequester.requestFocus()

        oldPieces.forEach { Piece.I.draw(it.first, it.second) }
        Piece.I.draw(curPiecePos.first, curPiecePos.second)
    }
}