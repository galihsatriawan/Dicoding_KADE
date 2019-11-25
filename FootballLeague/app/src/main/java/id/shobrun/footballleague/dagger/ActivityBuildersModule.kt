package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.dagger.auth.AuthModule
import id.shobrun.footballleague.dagger.auth.AuthViewModelModule
import id.shobrun.footballleague.ui.auth.AuthActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent
    @ContributesAndroidInjector (modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun authActivity() : AuthActivity

}