package xyz.czanik.carapp.mvi

interface Reducer<ViewState, Result> {
    fun reduce(viewState: ViewState, result: Result): ViewState
}