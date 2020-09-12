package xyz.czanik.carapp.carbrands

import io.reactivex.rxjava3.core.Observable
import xyz.czanik.carapp.mvi.Processor
import xyz.czanik.carapp.mvi.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event.CarBrandClicked
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result.CarBrandSelected

class CarBrandClickedProcessor : Processor<CarBrandClicked, TaskResult<CarBrandSelected>> {

    override fun process(events: Observable<CarBrandClicked>): Observable<TaskResult<CarBrandSelected>> = events.map(::selectCarBrand)

    private fun selectCarBrand(event: CarBrandClicked) = TaskResult.Success(CarBrandSelected(event.index))
}