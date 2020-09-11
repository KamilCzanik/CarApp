package xyz.czanik.carapp.carbrands

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.ofType
import xyz.czanik.carapp.Navigator
import xyz.czanik.carapp.Processor
import xyz.czanik.carapp.Repository
import xyz.czanik.carapp.TaskResult

class CarBrandsProcessor(
    private val navigator: Navigator,
    private val carBrandsRepository: Repository<Unit, List<CarBrand>>
) : Processor<CarBrandsContract.Event, TaskResult<CarBrandsContract.Result>> {

    override fun process(events: Observable<CarBrandsContract.Event>): Observable<TaskResult<CarBrandsContract.Result>> = Observable.merge(
        InitProcessor(carBrandsRepository).process(events.ofType()),
        CarBrandClickedProcessor().process(events.ofType()),
        ConfirmClickedProcessor(navigator).process(events.ofType())
    )
}