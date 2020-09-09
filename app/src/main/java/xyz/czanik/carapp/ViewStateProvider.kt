package xyz.czanik.carapp

import androidx.lifecycle.LiveData

interface ViewStateProvider<ViewState> {
    val viewState: LiveData<ViewState>
}