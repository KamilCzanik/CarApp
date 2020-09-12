package xyz.czanik.carapp.mvi

import androidx.lifecycle.LiveData

interface ViewStateProvider<ViewState> {
    val viewState: LiveData<ViewState>
}