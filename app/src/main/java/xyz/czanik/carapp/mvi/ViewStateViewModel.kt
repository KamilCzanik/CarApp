package xyz.czanik.carapp.mvi

import androidx.core.util.Consumer
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import xyz.czanik.carapp.toLiveData

abstract class ViewStateViewModel<ViewState, Event, Result>(
    initialViewState: ViewState,
    processor: Processor<Event, TaskResult<Result>>,
    reducer: Reducer<ViewState, TaskResult<Result>>
) : ViewModel(), ViewStateProvider<ViewState>, Consumer<Event> {

    private val backingViewStateSubject = PublishSubject.create<ViewState>()
    override val viewState: LiveData<ViewState> = backingViewStateSubject.toLiveData()
    private val events = PublishSubject.create<Event>()
    private val disposables = CompositeDisposable()

    init {
        events
                .compose(processor::process)
                .scan(initialViewState, reducer::reduce)
                .subscribe(::postViewState)
                .manageDisposable()
    }

    override fun accept(event: Event) = events.onNext(event)

    override fun onCleared() = disposables.clear()

    private fun postViewState(viewState: ViewState) = backingViewStateSubject.onNext(viewState)

    private fun Disposable.manageDisposable() = addTo(disposables)
}