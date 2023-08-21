import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class GridView:IView, GridPane(){
    override var col:Int? = null
    override var row:Int? = null
    override var selectedShape:String? = null
    private var model: Model? = null
    private val rectangles = Array(50) { row->
        Array(75) {col ->
            Rectangle(20.0, 16.0)
        }
    }


    init {
        this.isGridLinesVisible = true
        this.style
        this.hgap = 1.0
        this.vgap = 1.0
        for(i in 0..49) {
            for (j in 0..74) {
                rectangles[i][j].fill = Color.WHITE
                this.add(rectangles[i][j],j,i)
                rectangles[i][j].setOnMouseClicked {
                    row = i
                    col = j
                    positionClicked(i, j)
                }
            }
        }
        this.style = "-fx-border-color: grey;"
    }

    private fun positionClicked(i:Int, j:Int) {
        model!!.addShapeToBoard(i,j)
    }

    override fun addModel(myModel: Model) {
        model = myModel
    }

    override fun update() {
        for(i in 0..49) {
            for(j in 0..74) {
                if (!model!!.board[i][j]) {
                    rectangles[i][j].fill = Color.WHITE
                } else {
                    rectangles[i][j].fill = Color.BLACK
                }
            }
        }
    }
}