package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.dagger.auth.AuthModule
import id.shobrun.footballleague.dagger.auth.AuthScope
import id.shobrun.footballleague.dagger.auth.AuthViewModelModule
import id.shobrun.footballleague.dagger.league.LeagueScope
import id.shobrun.footballleague.ui.auth.AuthActivity
import id.shobrun.footballleague.views.LeagueActivity
import id.shobrun.footballleague.views.leagues.DetailLeagueViewModel
import id.shobrun.footballleague.views.leagues.DetailLeaguesActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent
    @AuthScope
    @ContributesAndroidInjector (modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun authActivity() : AuthActivity

    @LeagueScope
    @ContributesAndroidInjector
    abstract fun leagueActivity() : LeagueActivity

    @ContributesAndroidInjector (modules = [DetailLeagueViewModel::class])
    abstract fun detailLeagueActivity (): DetailLeaguesActivity
}