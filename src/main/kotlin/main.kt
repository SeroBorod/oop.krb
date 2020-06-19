import component.root
import data.*
import react.dom.render
import react.router.dom.browserRouter
import redux.*
import wrapper.reduxLogger
import kotlin.browser.document

val store = createStore(
    ::changeReducer,
    State(Array(10){Array(10){0}},
        testList,
        Array(10){0}),
    compose(
        rEnhancer(),
        applyMiddleware(
            reduxLogger.logger as Middleware<State, Action, Action, Action, Action>
        )
    )
)

val rootDiv =
    document.getElementById("root")

fun render() = render(rootDiv) {
    browserRouter {
        root(store)
    }
}

fun main() {
    render()
    store.subscribe {
        render()
    }
}