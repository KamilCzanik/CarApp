package xyz.czanik.carapp.carbrands

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import xyz.czanik.carapp.mvi.Error
import xyz.czanik.carapp.mvi.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result.CarBrandSelected
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result.CarBrands

internal class CarBrandsReducerTest {

    private val SUT = CarBrandsReducer()

    @Test
    fun `test SUT reduces loading`() {
        assertEquals(viewState(isLoading = true), SUT.reduce(viewState(), TaskResult.InProgress()))
    }

    @Test
    fun `test SUT reduces error`() {
        assertEquals(viewState(error = Error("")), SUT.reduce(viewState(), TaskResult.Failure(Throwable())))
    }

    @Test
    fun `test SUT reduces success with car brands`() {
        val carBrands = listOf(CarBrand(""))
        assertEquals(viewState(carBrands = carBrands), SUT.reduce(viewState(), TaskResult.Success(CarBrands(carBrands))))
    }

    @Test
    fun `test SUT reduces error and invalidates loading`() {
        assertEquals(viewState(error = Error("")), SUT.reduce(viewState(isLoading = true), TaskResult.Failure(Throwable())))
    }

    @Test
    fun `test SUT reduces success with car brands and invalidates loading`() {
        val carBrands = listOf(CarBrand(""))
        assertEquals(viewState(carBrands = carBrands), SUT.reduce(viewState(isLoading = true), TaskResult.Success(CarBrands(carBrands))))
    }

    @Test
    fun `test SUT reduces loading and invalidates error`() {
        assertEquals(viewState(isLoading = true), SUT.reduce(viewState(error = Error("")), TaskResult.InProgress()))
    }

    @Test
    fun `test SUT reduces success with selected car brand and keeps car brands`() {
        val carBrands = listOf(CarBrand(""))
        val selectedIndex = 0
        assertEquals(
            viewState(carBrands = carBrands, selectedBrand = carBrands[selectedIndex]),
            SUT.reduce(viewState(carBrands = carBrands), TaskResult.Success(CarBrandSelected(selectedIndex)))
        )
    }

    private fun viewState(
        carBrands: List<CarBrand>? = null,
        selectedBrand: CarBrand? = null,
        error: Error? = null,
        isLoading: Boolean = false
    ) = CarBrandsContract.ViewState(carBrands, selectedBrand, error, isLoading)
}