package xyz.czanik.carapp

interface Reducer<ViewState, Result> {
    fun reduce(viewState: ViewState, result: Result): ViewState
}