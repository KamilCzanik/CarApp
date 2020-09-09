package xyz.czanik.carapp

import io.reactivex.rxjava3.core.Observable

interface Processor<Event, Result> {
    fun process(event: Event): Observable<Result>
}