package xyz.czanik.carapp.carbrands

import xyz.czanik.carapp.Container
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result
import xyz.czanik.carapp.carbrands.CarBrandsContract.ViewState
import xyz.czanik.carapp.carbrands.processors.CarBrandsProcessor
import xyz.czanik.carapp.mvi.ViewStateViewModel

class CarBrandsViewModel(
    container: Container
) : ViewStateViewModel<ViewState, Event, Result>(
    ViewState.DEFAULT,
    CarBrandsProcessor(container.navigator, container.repositoryFactory.carBrands),
    CarBrandsReducer()
)

