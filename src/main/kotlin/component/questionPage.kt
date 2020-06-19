package component

import data.Test
import hoc.withDisplayName
import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.navLink

interface questionPageProps : RProps {
    var arr: Test
    var head: String
    var path: String
    var submit: (Event) -> Unit
    var next: (Event) -> Unit
    var prev: (Event) -> Unit
    var pageNumber:Int
}

fun questionPageFC() =
    functionalComponent<questionPageProps> { props ->
        div("container") {
            attrs.id = "container__questions[${props.pageNumber}]"
            h2 { +props.head ;attrs.id = "zxc"}
            p("questions[${props.pageNumber}]"){ +props.arr.questions[props.pageNumber].questionTitle }
            ul("variants"){
                props.arr.questions[props.pageNumber].variants.mapIndexed { i, j ->
                    li("inputGroup") {
                        label {
                            input(type = InputType.radio, classes = "option-input radio") {
                            attrs.id = "ans>$i<"
                            attrs.name = "question"
                            }
                            +j
                        }
                    }
                }
                attrs.id = "ans"
            }

            div("button-holder"){
                div("divButton b3"){
                    if (props.pageNumber != 0) {
                        navLink("${props.pageNumber - 1}") {
                                button(classes = "button third") {
                                    +"←"
//                                    console.log("prev_path = ${props.path}")
                                    attrs.id = "prevButton"
                                    attrs.value = "${props.pageNumber}>${props.path}<"
//                                    console.log("butt_prev attrs.value = ${attrs.value}\nbutt id = ${attrs.id}")
                                    attrs.onClickFunction = props.prev
                                }
                            }
                        }
                    else{
                        div("lorem"){
                            navLink(props.path.substringBeforeLast('/')) {
                                button(classes = "button third") {
                                    +"Вернуться"
                                }
                            }
                        }
                    }
                }

                div("divButton b2"){
                button(classes = "button second") {
                    +"submit"
//                    console.log("path = ${props.path}")//test0/0
                    attrs.id = "submitButton"
                    attrs.value = "${props.arr.questions[props.pageNumber].variants.size}|${((props.path.substringAfterLast("t"))
                            .substringBefore("/"))}|${props.pageNumber}"//vars|test|page
//                    console.log("butt attrs.value = ${attrs.value}\nbutt id = ${attrs.id}")
                    attrs.onClickFunction = props.submit
                    }
                }
                div("divButton b1"){
                    if (props.pageNumber < props.arr.questions.size - 1) {
                    navLink("${props.pageNumber + 1}") {

                            button(classes = "button first") {
                                +"→"
//                                console.log("path = ${props.path}")
                                attrs.id = "nextButton"
                                attrs.value = "${props.pageNumber}>${props.path}<"
//                                console.log("butt attrs.value = ${attrs.value}\nbutt id = ${attrs.id}")
                                attrs.onClickFunction = props.next
                            }
                        }
                    }else{
                        div("lorem"){
                            navLink("result") {

                                button(classes = "button first") {
                                    +"Завершить"
//                                    console.log("path = ${props.path}")
                                    attrs.id = "nextButton"
                                    attrs.value = "${props.pageNumber}>${props.path}<"
//                                    console.log("butt attrs.value = ${attrs.value}\nbutt id = ${attrs.id}")
                                    attrs.onClickFunction = props.next
                                }
                            }
                        }
                    }
                }
            }
        }
    }

fun RBuilder.questionPage(
    header: String,
    path: String,
    pageNumber:Int,
    arr: Test,
    next: (Event) -> Unit,
    submit: (Event) -> Unit,
    prev: (Event) -> Unit
) = child(
    withDisplayName(path.substring(1 until path.length), questionPageFC())
) {
    attrs.arr = arr
    attrs.head = header
    attrs.path = path
    attrs.submit = submit
    attrs.next = next
    attrs.prev = prev
    attrs.pageNumber = pageNumber
}