import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.HBox

class StatusView : IView, HBox() {
    private var model: Model? = null
    private var frameCount:Int = 0
    override var col:Int? = null
    override var row:Int? = null
    override var selectedShape:String? = null
    private var statusMessage:String = ""
    private var frameCountMessage:String = "Frame $frameCount"
    private var statusLabel = Label(statusMessage)
    private var frameCountLabel = Label(frameCountMessage)
    private var initialUpdate: Boolean = true
    init {
        this.height = 10.0
        statusLabel.alignment = Pos.CENTER_LEFT
        statusLabel.padding = Insets(5.0)
        statusLabel.prefWidth = 1435.0
        frameCountLabel.alignment = Pos.CENTER_RIGHT
        frameCountLabel.padding = Insets(5.0)
        frameCountLabel.prefWidth = 100.0
        this.children.add(statusLabel)
        this.children.add(frameCountLabel)
    }

    override fun addModel(myModel: Model) {
        model = myModel
    }

    override fun update() {
        frameCount++
        frameCountMessage = "Frame $frameCount"
        if (selectedShape!=null && row!=null && col !=null) {
            initialUpdate = false
            statusMessage = "Created $selectedShape at $col, $row"
        } else {
            if (!initialUpdate) {
                statusMessage = "Cleared"
            }
        }
        statusLabel.text = statusMessage
        frameCountLabel.text = frameCountMessage
//        println(statusMessage)
//        println(frameCountMessage)
    }
}