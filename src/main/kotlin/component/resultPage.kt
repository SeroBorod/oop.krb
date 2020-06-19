package component

import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent

interface ResultPageProps : RProps {
    var points: Array<Int>
}

fun resultPageFC(header:String, testNum:Int) =
    functionalComponent<ResultPageProps> {
        div("resContainer") {
            h2 { +header }
            +"Набрано баллов:${it.points.sum()}\n"
            table("table") {
                tr {
                    th { +"Вопрос" }
                    th { +"Баллы" }
                }
                for (i in 0 until testNum) {
                    tr {
                        td {
                            +"${i+1}"
                        }
                        td {
                            +"${it.points[i]}"
                        }
                    }
                }
            }
        }
    }

fun RBuilder.resultPage(
    header:String,
    points: Array<Int>,
    testNum:Int
) = child(
    withDisplayName("resultPage",  resultPageFC(header,testNum))
){
    attrs.points = points
}