package xyz.czanik.carapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable

fun <ViewState> Observable<ViewState>.toLiveData(): LiveData<ViewState> {
    return LiveDataReactiveStreams.fromPublisher(toFlowable(BackpressureStrategy.LATEST))
}