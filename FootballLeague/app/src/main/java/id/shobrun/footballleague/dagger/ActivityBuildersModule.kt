package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.dagger.auth.AuthModule
import id.shobrun.footballleague.dagger.auth.AuthScope
import id.shobrun.footballleague.dagger.auth.AuthViewModelModule
import id.shobrun.footballleague.dagger.league.LeagueFragmentModule
import id.shobrun.footballleague.dagger.league.LeagueScope
import id.shobrun.footballleague.dagger.league.detail.DetailLeagueModule
import id.shobrun.footballleague.dagger.league.detail.DetailLeagueViewModelModule
import id.shobrun.footballleague.ui.auth.AuthActivity
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import id.shobrun.footballleague.views.LeagueActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent
    @AuthScope
    @ContributesAndroidInjector (modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun authActivity() : AuthActivity

    @LeagueScope
    @ContributesAndroidInjector
    abstract fun leagueActivity() : LeagueActivity

    @ContributesAndroidInjector (modules = [LeagueFragmentModule::class,DetailLeagueModule::class,DetailLeagueViewModelModule::class])
    abstract fun detailLeagueActivity (): DetailLeagueActivity
}