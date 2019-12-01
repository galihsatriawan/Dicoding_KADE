package id.shobrun.footballleague

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.shobrun.footballleague.di.AppComponent
import id.shobrun.footballleague.di.DaggerAppComponent
import timber.log.Timber

class BaseApplication: DaggerApplication(){
    private val component : AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }


}
