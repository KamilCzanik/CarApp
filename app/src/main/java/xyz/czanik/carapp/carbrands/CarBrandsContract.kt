package xyz.czanik.carapp.carbrands

import xyz.czanik.carapp.Error

interface CarBrandsContract {

    sealed class Event {
        object Init : Event()
        data class CarBrandClicked(val index: Int) : Event()
        data class ConfirmClicked(val carBrand: CarBrand) : Event()
    }

    data class ViewState(
        val carBrands: List<CarBrand>?,
        val selectedBrand: CarBrand?,
        val error: Error?,
        val isLoading: Boolean
    ) {

        companion object {
            val DEFAULT = ViewState(null, null, null, false)
        }
    }

    sealed class Result {
        data class CarBrands(val brands: List<CarBrand>?) : Result()
        data class CarBrandSelected(val index: Int) : Result()
    }
}