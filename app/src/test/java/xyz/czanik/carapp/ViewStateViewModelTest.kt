package xyz.czanik.carapp

import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class ViewStateViewModelTest {

    private val processor = spy(StubProcessor())
    private val reducer = spy(StubReducer())
    private val SUT = ViewStateViewModelImpl(StubViewState.DEFAULT, processor, reducer)

    @Test
    fun `test no view state without event`() {
        SUT.viewState.test().assertNoValue()
    }

    @Test
    fun `test view state is provided after event`() {
        val tester = SUT.viewState.test()
        SUT.accept(StubEvent(1))
        tester.assertValue(StubViewState(1))
    }

    @Test
    fun `test SUT provides events to processor`() {
        verifyZeroInteractions(processor)
        SUT.accept(StubEvent(1))
        verify(processor).process(StubEvent(1))
        clearInvocations(processor)
        SUT.accept(StubEvent(2))
        verify(processor).process(StubEvent(2))
        verifyNoMoreInteractions(processor)
    }

    @Test
    fun `test processor results are passed to reducer`() {
        verifyZeroInteractions(reducer)
        SUT.accept(StubEvent(1))
        verify(reducer).reduce(any(), argThat { id == 1 })
        clearInvocations(reducer)
        SUT.accept(StubEvent(2))
        verify(reducer).reduce(any(), argThat { id == 2 })
        verifyNoMoreInteractions(reducer)
    }

    @Test
    fun `test reduced view state is provided`() {
        val tester = SUT.viewState.test()
        SUT.accept(StubEvent(1))
        SUT.accept(StubEvent(2))
        tester.assertValue(StubViewState(2))
        SUT.accept(StubEvent(3))
        tester.assertValue(StubViewState(3))
    }

    private data class StubEvent(val id: Int)

    private data class StubResult(val id: Int)

    private data class StubViewState(val id: Int) {
        companion object {
            val DEFAULT = StubViewState(0)
        }
    }

    private open class StubProcessor : Processor<StubEvent, StubResult> {

        override fun process(event: StubEvent): Observable<StubResult> = Observable.just(StubResult(event.id))
    }

    private open class StubReducer : Reducer<StubViewState, StubResult> {

        override fun reduce(viewState: StubViewState, result: StubResult): StubViewState {
            return viewState.copy(id = result.id)
        }
    }

    private class ViewStateViewModelImpl(
        initialViewState: StubViewState,
        processor: Processor<StubEvent, StubResult>,
        reducer: Reducer<StubViewState, StubResult>
    ) : ViewStateViewModel<StubViewState, StubEvent, StubResult>(initialViewState, processor, reducer)
}