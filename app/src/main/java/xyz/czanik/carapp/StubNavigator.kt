package xyz.czanik.carapp

import xyz.czanik.carapp.carbrands.CarBrand

class StubNavigator : Navigator {

    override fun navigateToFillCarData(carBrand: CarBrand) = Unit
}