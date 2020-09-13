package xyz.czanik.carapp.carbrands

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.jupiter.api.Test
import xyz.czanik.carapp.Navigator
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event.ConfirmClicked
import xyz.czanik.carapp.carbrands.processors.ConfirmClickedProcessor

internal class ConfirmClickedProcessorTest {

    private val navigator: Navigator = mock()
    private val SUT = ConfirmClickedProcessor(navigator)
    private val events = PublishSubject.create<ConfirmClicked>()
    private val resultTester = SUT.process(events).test()

    @Test
    fun `test SUT does not interact with navigator without event`() {
        verifyZeroInteractions(navigator)
    }

    @Test
    fun `test SUT navigates after event`() {
        val carBrand = CarBrand("VW","")
        events.onNext(ConfirmClicked(carBrand))
        verify(navigator).navigateToFillCarData(carBrand)
    }

    @Test
    fun `test SUT does not return any result`() {
        events.onNext(ConfirmClicked(CarBrand("VW","")))
        resultTester.assertNoValues()
    }
}