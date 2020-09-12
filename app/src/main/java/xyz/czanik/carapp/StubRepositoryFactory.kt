package xyz.czanik.carapp

import io.reactivex.rxjava3.core.Single
import xyz.czanik.carapp.carbrands.CarBrand

class StubRepositoryFactory : RepositoryFactory {

    override val carBrands = object : Repository<Unit, List<CarBrand>> {
        override fun get(request: Unit): Single<List<CarBrand>> = Single.just(
            listOf(
                CarBrand("Mazda"),
                CarBrand("Audi"),
                CarBrand("Toyota"),
                CarBrand("Mini"),
                CarBrand("Mercedes"),
                CarBrand("Honda")
            )
        )
    }
}