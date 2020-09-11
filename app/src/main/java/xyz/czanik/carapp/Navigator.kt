package xyz.czanik.carapp

import xyz.czanik.carapp.carbrands.CarBrand

interface Navigator {

    fun navigateToFillCarData(carBrand: CarBrand)
}