package id.shobrun.footballleague

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.shobrun.footballleague.dagger.AppComponent
import id.shobrun.footballleague.dagger.DaggerAppComponent


class FootballApplication : DaggerApplication(){
    private val component : AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }


}