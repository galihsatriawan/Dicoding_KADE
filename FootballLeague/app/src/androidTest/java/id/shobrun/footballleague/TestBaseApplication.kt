package id.shobrun.footballleague

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.shobrun.footballleague.di.AppComponent
import id.shobrun.footballleague.di.DaggerAppComponent

class TestBaseApplication : DaggerApplication(){
    private val component  : AppComponent by lazy{ DaggerAppComponent.builder()
        .application(this)
        .build()
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }

}