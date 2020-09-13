package xyz.czanik.carapp.carbrands

import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.jupiter.api.Test
import xyz.czanik.carapp.mvi.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.*
import xyz.czanik.carapp.carbrands.processors.CarBrandClickedProcessor

internal class CarBrandClickedProcessorTest {

    private val SUT = CarBrandClickedProcessor()
    private val events = PublishSubject.create<Event.CarBrandClicked>()
    private val resultTester = SUT.process(events).test()

    @Test
    fun `test no results without event`() {
        resultTester.assertNoValues()
    }

    @Test
    fun `test SUT returns success result with clicked index`() {
        events.onNext(Event.CarBrandClicked(1))
        resultTester.assertValue(TaskResult.Success(Result.CarBrandSelected(1)))
    }
}