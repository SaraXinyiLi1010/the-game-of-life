import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.util.Duration
import java.security.Key


class Main : Application() {
    override fun start(stage: Stage?) {
        val model = Model()

        // our layout is the root of the scene graph
        val root = VBox()

        // views are the children of the top-level layout
        val toolbar = ToolbarView()
        toolbar.addModel(model)
        val gridView = GridView()
        gridView.addModel(model)
        val status = StatusView()
        status.addModel(model)
        // register views with the model
        model.addView(toolbar)
        model.addView(gridView)
        model.addView(status)

        // setup and display
        root.children.addAll(toolbar, gridView, status) // gridView
        stage?.scene = Scene(root)
        stage?.isResizable = false
        stage?.width = 1575.0
        stage?.height = 950.0
        stage?.title = "Conway's Game of Life x784li"
        stage?.show()
        model.startViewAuto()
        root.setOnKeyPressed { event ->
            if  (event.code == KeyCode.M) {
                println("M pressed")
                model.stopViewAuto()
                root.addEventFilter(KeyEvent.KEY_PRESSED) { event ->
                    if (event.code === KeyCode.SPACE) {
                        println("SPACE pressed")
                        model.notifyViews()
                    }
                }
                root.addEventFilter(KeyEvent.KEY_PRESSED) { event ->
                    if (event.code == KeyCode.Q) {
                        println("Q pressed")
                        model.startViewAuto()
                    }
                }
            }
        }

    }
}