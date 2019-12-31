package id.shobrun.footballleague.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import id.shobrun.footballleague.TestBaseApplication

@Suppress("unused")
class AndroidJUnitTestRunner : AndroidJUnitRunner() {
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestBaseApplication::class.java.name, context)
    }


}