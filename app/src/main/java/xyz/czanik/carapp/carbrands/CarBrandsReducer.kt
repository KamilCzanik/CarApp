package xyz.czanik.carapp.carbrands

import xyz.czanik.carapp.mvi.Error
import xyz.czanik.carapp.mvi.Reducer
import xyz.czanik.carapp.mvi.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result
import xyz.czanik.carapp.carbrands.CarBrandsContract.ViewState

class CarBrandsReducer : Reducer<ViewState, TaskResult<Result>> {

    override fun reduce(viewState: ViewState, result: TaskResult<Result>): ViewState = when (result) {
        is TaskResult.Success -> viewState.renderSuccess(result)
        is TaskResult.InProgress -> viewState.renderInProgress()
        is TaskResult.Failure -> viewState.renderError(result)
    }

    private fun ViewState.renderSuccess(success: TaskResult.Success<Result>): ViewState = when (val result = success.result) {
        is Result.CarBrands -> copy(isLoading = false, error = null, carBrands = result.brands)
        is Result.CarBrandSelected -> copy(selectedBrand = requireNotNull(carBrands)[result.index])
    }

    private fun ViewState.renderInProgress() = copy(isLoading = true, error = null)

    private fun ViewState.renderError(result: TaskResult.Failure<Result>) = copy(
        isLoading = false,
        error = Error(result.cause.message.orEmpty())
    )
}