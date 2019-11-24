package id.shobrun.footballleague.dagger


import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.shobrun.footballleague.FootballApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<FootballApplication>{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app : Application) : Builder

        fun build() : AppComponent
    }
}