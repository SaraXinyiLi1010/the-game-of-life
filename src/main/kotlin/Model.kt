import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.util.Duration

class Model {
    // represent my board
    private val sizeOuter = 50
    private val sizeInner = 75
    private val views = ArrayList<IView>()
    val timeline = Timeline(
        KeyFrame(
            Duration.seconds(1.0),
            { event: ActionEvent? ->
                notifyViews()
            })
    )
    var selectedShape:String? = null
    val board = Array(sizeOuter) { BooleanArray(sizeInner) }
    var lastSelectedRow: Int? = null
    var lastSelectedCol: Int? = null


    // board manipulation
    // (a) add a shape
    // (b) clear the board

    // view management
    fun addView(view: IView) {
        views.add(view)
    }

    fun clearBoard() {
        for (i in 0 until sizeOuter) {
            for (j in 0 until sizeInner) {
                board[i][j] = false
            }
        }
        selectedShape = null
        lastSelectedRow = null
        lastSelectedCol = null
        notifyViews()
    }
    fun addShapeToBoard(row:Int, col:Int) {
        lastSelectedCol = col
        lastSelectedRow = row
        if (selectedShape!= null) {
            if (selectedShape == "Block") {
                board[row][col] = true
                if (row+1 < sizeOuter)
                    board[row+1][col] = true
                if (row+1 < sizeOuter && col+1 < sizeInner)
                    board[row+1][col+1] = true
                if(col+1 < sizeInner)
                    board[row][col+1] = true
            }
            //  need to check whether out of bond!!!!!
            if (selectedShape == "Beehive") {
                println("row "+ row)
                if (col + 1 < sizeInner)
                    board[row][col+1] = true
                if  (col + 2 < sizeInner)
                    board[row][col+2] = true
                if (row+1 < sizeOuter)
                    board[row+1][col] = true
                if  (row+1 < sizeOuter && col+3 < sizeInner)
                    board[row+1][col+3] = true
                if  (row+2 < sizeOuter && col+1 < sizeInner)
                    board[row+2][col+1] = true
                if  (row + 2 < sizeOuter && col+1 < sizeInner)
                    board[row+2][col+2] = true
            }

            if (selectedShape == "Blinker") {
                if (row+1 < sizeOuter)
                    board[row+1][col] = true
                if (row+1 < sizeOuter && col+1 < sizeInner)
                    board[row+1][col+1] = true
                if (row+1 < sizeOuter && col+2 < sizeInner)
                    board[row+1][col+2] = true
            }

            if (selectedShape == "Toad") {
                if (col + 1 < sizeInner)
                    board[row][col+1] = true
                if (col + 2 < sizeInner)
                    board[row][col+2] = true
                if (col + 3 < sizeInner)
                    board[row][col+3] = true
                if (row + 1 < sizeOuter)
                    board[row+1][col] = true
                if (row + 1 < sizeOuter && col < sizeInner)
                    board[row+1][col] = true
                if  (row + 1 < sizeOuter && col + 1 < sizeInner)
                    board[row+1][col+1] = true
                if (row+1 < sizeOuter && col+2 < sizeInner)
                    board[row+1][col+2] = true
            }

            if  (selectedShape == "Glinder") {
                if (row+1 < sizeOuter)
                    board[row+1][col] = true
                if (row+2 < sizeOuter && col+1< sizeInner)
                    board[row+2][col+1] = true
                if (row+2 < sizeOuter && col+2 < sizeInner)
                    board[row+2][col+2] = true
                if (row+1 < sizeOuter && col+2 < sizeInner)
                    board[row+1][col+2] = true
                if (col+2 < sizeInner)
                    board[row][col+2] = true
            }
            notifyViews()
        }
    }

    private fun applyRulesToBoard() {
        val temp = Array(sizeOuter) { BooleanArray(sizeInner) }
        for (i in 0 until sizeOuter) {
            for (j in 0 until sizeInner) {
                var countLiveNeighbors = 0
                if (i-1 >= 0 && board[i-1][j])
                    countLiveNeighbors++
                if (j-1 >= 0 && board[i][j-1])
                    countLiveNeighbors++
                if  (i-1>=0 && j-1>=0 && board[i-1][j-1])
                    countLiveNeighbors++
                if  (i+1 < sizeOuter && board[i+1][j])
                    countLiveNeighbors++
                if  (j+1 <  sizeInner && board[i][j+1])
                    countLiveNeighbors++
                if  (i+1 < sizeOuter && j+1 < sizeInner && board[i+1][j+1])
                    countLiveNeighbors++
                if  (i-1 >= 0 && j+1 < sizeInner && board[i-1][j+1])
                    countLiveNeighbors++
                if  (i+1<sizeOuter && j-1 >= 0 && board[i+1][j-1])
                    countLiveNeighbors++
                if (board[i][j] && (countLiveNeighbors == 2 ||countLiveNeighbors == 3))
                    temp[i][j] = true
                if (!board[i][j] && countLiveNeighbors == 3)
                    temp[i][j] = true
            }
        }
        for (i in 0 until sizeOuter) {
            for (j in 0 until sizeInner) {
                board[i][j] = temp[i][j]
            }
        }

    }
    fun startViewAuto() {
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
    }

    fun stopViewAuto() {
        timeline.stop()
    }

    fun notifyViews() {
        for (view in views) {
            if(lastSelectedCol !=null && lastSelectedRow!= null && selectedShape != null) {
                view.selectedShape = selectedShape!!
                view.row = lastSelectedRow
                view.col = lastSelectedCol
            } else if (lastSelectedCol == null && lastSelectedRow == null && selectedShape == null) {
                view.selectedShape = null
                view.row = null
                view.col = null
            }
            view.update()
        }
        applyRulesToBoard()
    }
}