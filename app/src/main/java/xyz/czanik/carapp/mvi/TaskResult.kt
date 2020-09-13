package xyz.czanik.carapp.mvi

sealed class TaskResult<out Result> {

    data class Success<out Result>(val result: Result) : TaskResult<Result>()

    data class InProgress<out Result>(val result: Result? = null) : TaskResult<Result>()

    data class Failure<out Result>(val cause: Throwable) : TaskResult<Result>()
}