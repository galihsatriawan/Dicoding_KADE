package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.views.MainActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun injectMainActivity() : MainActivity
}