package xyz.czanik.carapp.carbrands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.czanik.carapp.Container

class CarAppViewModelFactory(
    private val container: Container
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(CarBrandsViewModel::class.java) -> modelClass.cast(CarBrandsViewModel(container))!!
        else -> throw IllegalArgumentException("Could not recognize ${ modelClass.simpleName}")
    }
}