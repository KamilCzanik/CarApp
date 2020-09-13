package xyz.czanik.carapp.carbrands.processors

import io.reactivex.rxjava3.core.Observable
import xyz.czanik.carapp.mvi.Processor
import xyz.czanik.carapp.Repository
import xyz.czanik.carapp.carbrands.CarBrand
import xyz.czanik.carapp.mvi.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result

class InitProcessor(
    private val carBrandsRepository: Repository<Unit, List<CarBrand>>
) : Processor<Event.Init, TaskResult<Result>> {

    override fun process(events: Observable<Event.Init>): Observable<TaskResult<Result>> = events.switchMap { fetchCarBrands() }

    private fun fetchCarBrands(): Observable<TaskResult<Result>> = carBrandsRepository.get(Unit)
            .toObservable()
            .map(::success)
            .onErrorReturn(::failure)
            .startWithItem(TaskResult.InProgress())

    private fun success(brands: List<CarBrand>?): TaskResult<Result> = TaskResult.Success(Result.CarBrands(brands))

    private fun failure(cause: Throwable): TaskResult.Failure<Result> = TaskResult.Failure(cause)
}