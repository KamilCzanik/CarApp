package xyz.czanik.carapp

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InstantExecutorExtension : AfterEachCallback, BeforeEachCallback {

    private val taskExecutor = object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

        override fun postToMainThread(runnable: Runnable) = runnable.run()

        override fun isMainThread(): Boolean = true
    }

    override fun beforeEach(context: ExtensionContext?) = setExecutor(taskExecutor)

    override fun afterEach(context: ExtensionContext?) = setExecutor(null)

    private fun setExecutor(executor: TaskExecutor?) = ArchTaskExecutor.getInstance().setDelegate(executor)
}