package component

import data.*
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import org.w3c.dom.*
import react.*
import react.dom.*
import react.router.dom.*
import redux.*
import org.w3c.dom.events.Event
import react.RBuilder
import store
import kotlin.browser.document

interface RootProps : RProps {
    var store: Store<State, RAction, WrapperAction>
}

fun rootFC() =
    functionalComponent<RootProps> { props ->
        input(type = InputType.checkBox) { attrs.id = "side-checkbox"; }
        div("side-panel") {
            label("side-button-2") { attrs.htmlFor = "side-checkbox"; +"+" }
            div("side-title"){
               navLink(""){ +"Тесты:" }
            }
            h3("widget-title") { navLink(""){ +"Тесты:" } }
            ul("widget-list") {
                props.store.getState().tests.mapIndexed{i,j ->
                    li{navLink("/test$i"){ +"$j"}}
                }
                li{navLink("/results"){ +"Результаты"}}
            }
        }
        div("side-button-1-wr") {
            label("side-button-1"){
            div("side-b side-open"){+"open"}
            div("side-b side-close"){+"close"}
                attrs.htmlFor = "side-checkbox"
            }
        }

        switch {
            props.store.getState().tests.mapIndexed { i, j ->
                route("/test$i",
                    exact = true,
                    render = {
                        preTest(props.store.getState().tests[i], j.title, "/test$i",i)
                    }
                )
            }

            props.store.getState().tests.mapIndexed { i, j ->
                for(k in 0..j.questions.size){
                    route("/test$i/$k",
                        exact = true,
                        render = {
                            questionPage(
                                j.title,
                                "/test$i/$k",
                                k,
                                props.store.getState().tests[i],
                                props.next(),
                                props.submit(),
                                props.prev()
                            )
                        }
                    )
                }
                route("/test$i/result",
                    exact = true,
                    render = {
                        resultPage(
                            store.getState().tests[i].title,
                            store.getState().points[i],
                            store.getState().tests[i].questionQuantity
                        )
                    }
                )
            }
            route("/results",
            exact = true,
            render = {
                resultFull(
                    tests = store.getState().tests,
                    testNum = store.getState().tests.size,
                    points = store.getState().points
                )
            }
        )
    }
}

fun RootProps.next(): (Event) -> Unit = { _: Event ->//++
    val index = (document.getElementById("nextButton") as HTMLButtonElement).value
    val testNum = ((index.substringAfterLast("t")).substringBefore("/")).toInt()
    val pageNum = ((index.substringAfterLast("/")).substringBefore("<")).toInt()+1
//    console.log("index[next] = $index\n testNum = $testNum\n pageNum = $pageNum")
    store.dispatch(ChangePage(testNum,pageNum))
}

fun RootProps.prev(): (Event) -> Unit = { _: Event ->//++
    val index = (document.getElementById("prevButton") as HTMLButtonElement).value
    val testNum = ((index.substringAfterLast("t")).substringBefore("/")).toInt()
    val pageNum = (index.substringAfterLast("/")).substringBefore("<").toInt()-1
//    console.log("index[next] = $index\n testNum = $testNum\n pageNum = $pageNum")
    store.dispatch(ChangePage(testNum,pageNum))

}
fun RootProps.submit(): (Event) -> Unit = { _: Event ->
    val index = document.getElementById("submitButton") as HTMLButtonElement
    val testNum = index.value.substringAfter("|").substringBefore("|")//2 test //vars|test|page
    val pageNum = index.value.substringAfterLast('|')//page
    val questionsQuantity = index.value.substringBefore("|").toInt()//6 vars
//    console.log("num = ${questionsQuantity}\n testNum = $testNum\n pageNum = $pageNum")
    for(i in 0 until questionsQuantity){
         if((document.getElementById("ans>$i<") as HTMLInputElement).checked) {
            val selectedElem = document.getElementById("ans>$i<") as HTMLInputElement
//             console.log("yes-num = $i")
             if(i == store.getState().tests[testNum.toInt()].questions[pageNum.toInt()].answer) {
                 store.dispatch(SetPoints(
                     testNum.toInt(),
                     pageNum.toInt(),
                     store.getState().tests[testNum.toInt()].questions[pageNum.toInt()].qPoints
                 ))
                 selectedElem.id
             }else{
                 selectedElem.checked = false
             }
        }/*else {
            console.log("no-num = $i")
        }*/
    }
}

fun <T> removeElementByIndex(array:Array<T>, index:Int):Array<T>{
    val elem = array.toMutableList()
    elem.removeAt(index)
    return elem.toTypedArray()
}

fun RBuilder.root(store: Store<State, RAction, WrapperAction>) =
    child(withDisplayName("Root", rootFC())){
        attrs.store = store
    }






