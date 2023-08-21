import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File

class ToolbarView : IView, ToolBar() {
    var model:Model? = null
    override var col:Int? = null
    override var row:Int? = null
    override var selectedShape:String? = null

    init {
        val blockButton = Button("Block")
        blockButton.graphic = ImageView(Image("blockicon.png"))
        val beehiveButton = Button("Beehive")
        beehiveButton.graphic =  ImageView(Image("beehiveicon.png"))
        val blinkerButton = Button("Blinker")
        blinkerButton.graphic = ImageView(Image("blinkericon.png"))
        val toadButton = Button("Toad")
        toadButton.graphic = ImageView(Image("toadicon.png"))
        val glinderButton = Button("Glinder")
        glinderButton.graphic = ImageView(Image("glinder.png"))
        val clearButton = Button("Clear")
        clearButton.graphic = ImageView(Image("cancelicon.png"))
        blockButton.setOnAction { event ->
            selectedShape = "Block"
            model?.selectedShape = "Block"
        }
        beehiveButton.setOnAction{ event ->
            selectedShape = "Beehive"
            model?.selectedShape = "Beehive"
        }
        blinkerButton.setOnAction{ event ->
            selectedShape = "Blinker"
            model?.selectedShape = "Blinker"
        }
        toadButton.setOnAction { event->
            selectedShape = "Toad"
            model?.selectedShape = "Toad"
        }
        glinderButton.setOnAction { event ->
            selectedShape = "Glinder"
            model?.selectedShape = "Glinder"
        }

        clearButton.setOnAction { event ->
            model?.clearBoard()
        }


        // add buttons to toolbar
        this.items.add(blockButton)
        this.items.add(beehiveButton)
        this.items.add(blinkerButton)
        this.items.add(toadButton)
        this.items.add(glinderButton)
        this.items.add(clearButton)
    }

    override fun addModel(myModel:Model) {
        model = myModel
    }


    override fun update() {
        // update my button state
        // how do we get data from the model?
    }
}