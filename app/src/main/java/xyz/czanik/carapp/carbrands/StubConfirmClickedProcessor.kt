package xyz.czanik.carapp.carbrands

import io.reactivex.rxjava3.core.Observable
import xyz.czanik.carapp.Processor
import xyz.czanik.carapp.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event.ConfirmClicked

class StubConfirmClickedProcessor : Processor<ConfirmClicked, TaskResult<Nothing>> {

    override fun process(events: Observable<ConfirmClicked>): Observable<TaskResult<Nothing>> = events.flatMap { handleConfirmation() }

    private fun handleConfirmation(): Observable<TaskResult<Nothing>> = Observable.create<TaskResult<Nothing>> { emitter ->
        emitter.onComplete()
    }
}