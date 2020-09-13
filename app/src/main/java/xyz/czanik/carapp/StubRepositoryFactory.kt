package xyz.czanik.carapp

import io.reactivex.rxjava3.core.Single
import xyz.czanik.carapp.carbrands.CarBrand

class StubRepositoryFactory : RepositoryFactory {

    override val carBrands = object : Repository<Unit, List<CarBrand>> {
        override fun get(request: Unit): Single<List<CarBrand>> = Single.just(
            listOf(
                CarBrand("Mazda", "https://www.easylinedrawing.com/wp-content/uploads/2019/07/cartoon_car_drawing.png"),
                CarBrand("Audi", "https://www.easylinedrawing.com/wp-content/uploads/2019/07/cartoon_car_drawing.png"),
                CarBrand("Toyota", "https://www.easylinedrawing.com/wp-content/uploads/2019/07/cartoon_car_drawing.png"),
                CarBrand("Mini", "https://www.easylinedrawing.com/wp-content/uploads/2019/07/cartoon_car_drawing.png"),
                CarBrand("Mercedes", "https://www.easylinedrawing.com/wp-content/uploads/2019/07/cartoon_car_drawing.png"),
                CarBrand("Honda", "https://www.easylinedrawing.com/wp-content/uploads/2019/07/cartoon_car_drawing.png")
            )
        )
    }
}