package id.shobrun.footballleague.utils

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import timber.log.Timber
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit


/**
 * A Junit rule that registers an espresso idling resource which counts all tasks that are submitted
 * via [AppExecutors].
 */
class CountingAppExecutorsRule : TestWatcher() {
    // give it a unique id to workaround an espresso bug where you cannot register/unregister
    // an idling resource w/ the same name.
    private val id = UUID.randomUUID().toString()
    private val countingAppExecutors = CountingAppExecutors {
        callbacks.forEach {
            it.onTransitionToIdle()
        }
    }
    val appExecutors = countingAppExecutors.appExecutors
    private val callbacks = CopyOnWriteArrayList<IdlingResource.ResourceCallback>()
    private val idlingResource: IdlingResource = object : IdlingResource {
        override fun getName() = "App Executors Idling Resource $id"

        override fun isIdleNow() = countingAppExecutors.taskCount() == 0

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
            callbacks.add(callback)
        }
    }
    val TAG = this::class.java.name

    override fun starting(description: Description?) {
        Timber.d("$TAG register")
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        Timber.d("$TAG unregister")
        countingAppExecutors.drainTasks(10, TimeUnit.SECONDS)
        callbacks.clear()
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }
}