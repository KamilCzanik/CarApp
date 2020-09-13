package xyz.czanik.carapp

import xyz.czanik.carapp.carbrands.CarBrand

interface RepositoryFactory {

    val carBrands: Repository<Unit, List<CarBrand>>
}