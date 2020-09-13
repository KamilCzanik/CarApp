package xyz.czanik.carapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.czanik.carapp.carbrands.CarBrandsViewModel

class CarAppViewModelFactory(
    private val container: Container
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(CarBrandsViewModel::class.java) -> modelClass.cast(CarBrandsViewModel(container))!!
        else -> throw IllegalArgumentException("Could not recognize ${ modelClass.simpleName}")
    }
}