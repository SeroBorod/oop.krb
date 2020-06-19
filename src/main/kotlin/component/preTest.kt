package component

import data.Test
import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink
import store

interface PreTestProps : RProps {
    var currentTest: Test
}

fun preTestFC(header: String, path: String, page:Int) =
    functionalComponent<PreTestProps> {
        div("container preTestFC"){
            h2 { +it.currentTest.title }
            p{+it.currentTest.description}
            /*console.log("alyListPath = $path, path.substringAfterLast('t') = ${path.substringAfterLast('t')}")*/
            div("testButtonHolder"){
                if(store.getState().page[page]<store.getState().tests.size){
                    navLink("${path}/${
                    store.getState().page[path.substringAfterLast('t').toInt()]}",
                            className = "testButton")
                    { +"Начать тест" }
                }else{
                    navLink("${path}/result", className = "testButton")
                    { +"Смотреть результаты" }
                    navLink("${path}/0", className = "testButton")
                    { +"пройти снова" }
                }
            }
        }
    }

fun RBuilder.preTest(
        test: Test,
        header: String,
        path: String,
        page:Int// testNum
) = child(withDisplayName(header, preTestFC(header, path,page))){
    attrs.currentTest = test
}

