package id.shobrun.footballleague.di


import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun someString():String = "test"

    // Can access application from App Component so the return is false (is not null)
    @Singleton
    @Provides @Named("isAppNull")
    internal fun getApp(application: Application) : Boolean = application==null

}