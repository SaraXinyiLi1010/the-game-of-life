interface IView {
    var col:Int?
    var row:Int?
    var selectedShape:String?
    fun addModel(myModel: Model)
    fun update()
}