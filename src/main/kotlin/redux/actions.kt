package redux

class ChangePage(val index: Int,
                 val page:Int) : RAction

class SetPoints(val testIndex:Int,
                val questionIndex:Int,
                val points:Int) : RAction