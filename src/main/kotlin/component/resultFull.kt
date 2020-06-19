package component

import react.*
import react.dom.*
import data.*
import hoc.withDisplayName

interface ResultFullProps : RProps {
    var testNum: Int
    var points: Array<Array<Int>>
}

fun resultFullFC(tests:Array<Test>) =
    functionalComponent<ResultFullProps> {
	    div("container"){
		    for(i in 0 until it.testNum){
			    resultPage(
				    tests[i].title,
				    it.points[i],
				    tests[i].questionQuantity
			    )
		    }
	    }
    }

fun RBuilder.resultFull(
		tests:Array<Test>,
		testNum: Int,
		points: Array<Array<Int>>
) = child(
    withDisplayName("resultFull", resultFullFC(tests))
) {
        attrs.testNum = testNum
        attrs.points = points
    }
