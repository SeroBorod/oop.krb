package redux

import component.*
import data.*

fun changeReducer(state: State, action: RAction) =
    when (action) {
        is SetPoints -> State(
                setPoints(state.points,
                        action.testIndex,
                        action.questionIndex,
                        action.points),
                state.tests,
                state.page
        )
        is ChangePage -> State(
                state.points,
                state.tests,
                changeElement(state.page,
                        action.index,
                        action.page)
        )
        else -> state
    }
fun <T> changeElement(arr:Array<T>,index:Int,toChange:T):Array<T>{
    arr[index] = toChange
    return arr
}
fun setPoints(arr:Array<Array<Int>>,tIndex:Int,qIndex:Int,points:Int):Array<Array<Int>>{
    arr[tIndex][qIndex] = points
    return arr
}