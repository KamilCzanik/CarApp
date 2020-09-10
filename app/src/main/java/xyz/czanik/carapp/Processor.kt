package xyz.czanik.carapp

import io.reactivex.rxjava3.core.Observable

interface Processor<Event, Result> {
    fun process(events: Observable<Event>): Observable<Result>
}