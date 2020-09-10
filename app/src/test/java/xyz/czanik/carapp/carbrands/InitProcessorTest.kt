package xyz.czanik.carapp.carbrands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.jupiter.api.Test
import xyz.czanik.carapp.Repository
import xyz.czanik.carapp.TaskResult
import xyz.czanik.carapp.carbrands.CarBrandsContract.Result.CarBrands

internal class InitProcessorTest {

    private val repository: Repository<Unit, List<CarBrand>> = mock()
    private val events = PublishSubject.create<CarBrandsContract.Event.Init>()
    private val SUT = InitProcessor(repository)
    private val resultTester = SUT.process(events).test()

    @Test
    fun `test SUT does not interact with repository without event`() {
        verifyZeroInteractions(repository)
    }

    @Test
    fun `test SUT uses repository after event`() {
        arrangeWith(Single.never())
        verify(repository).get(Unit)
    }

    @Test
    fun `test SUT emits in progress on event`() {
        arrangeWith(Single.never())
        resultTester.assertValue(TaskResult.InProgress())
    }

    @Test
    fun `test SUT emits success result with car brands`() {
        val response = listOf(CarBrand("VW"))
        arrangeWith(Single.just(response))
        resultTester.assertValueAt(1, TaskResult.Success(CarBrands(response)))
    }

    @Test
    fun `test SUT emits failure result on error`() {
        val cause = Throwable("grrr")
        arrangeWith(Single.error(cause))
        resultTester.assertValueAt(1, TaskResult.Failure(cause))
    }

    private fun arrangeWith(just: Single<List<CarBrand>>) {
        repository respondWith just
        postEvent()
    }

    private fun postEvent() = events.onNext(CarBrandsContract.Event.Init)

    private infix fun Repository<Unit, List<CarBrand>>.respondWith(answer: Single<List<CarBrand>>) {
        whenever(get(any())) doReturn answer
    }
}