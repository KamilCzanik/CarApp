package xyz.czanik.carapp

import io.reactivex.rxjava3.core.Single

interface Repository<Request, Response> {

    fun get(request: Request): Single<Response>
}