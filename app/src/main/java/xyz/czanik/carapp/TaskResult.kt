package xyz.czanik.carapp

sealed class TaskResult<Result> {

    data class Success<Result>(val result: Result) : TaskResult<Result>()

    data class InProgress<Result>(val result: Result? = null, val exception: Throwable? = null) : TaskResult<Result>()

    data class Failure<Result>(val cause: Throwable) : TaskResult<Result>()
}