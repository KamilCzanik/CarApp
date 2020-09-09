package xyz.czanik.carapp

sealed class TaskResult<out Result, out Exception> {

    data class Success<out Result>(val result: Result) : TaskResult<Result, Nothing>()

    object InProgress : TaskResult<Nothing, Nothing>()

    data class Failure<out Exception>(val cause: Exception) : TaskResult<Nothing, Exception>()
}