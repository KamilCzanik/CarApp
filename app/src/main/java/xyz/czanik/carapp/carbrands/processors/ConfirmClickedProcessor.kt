package xyz.czanik.carapp.carbrands.processors

import io.reactivex.rxjava3.core.Observable
import xyz.czanik.carapp.Navigator
import xyz.czanik.carapp.mvi.Processor
import xyz.czanik.carapp.mvi.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event.ConfirmClicked

class ConfirmClickedProcessor(
    private val navigator: Navigator
) : Processor<ConfirmClicked, TaskResult<Nothing>> {

    override fun process(events: Observable<ConfirmClicked>): Observable<TaskResult<Nothing>> = events.flatMap(::navToFillCarData)

    private fun navToFillCarData(event: ConfirmClicked): Observable<TaskResult<Nothing>> = Observable.fromRunnable {
        navigator.navigateToFillCarData(event.carBrand)
    }
}