package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.dagger.league.list.LeagueFragmentModule
import id.shobrun.footballleague.dagger.league.list.LeagueScope
import id.shobrun.footballleague.dagger.league.detail.DetailLeagueModule
import id.shobrun.footballleague.dagger.league.detail.DetailLeagueScope
import id.shobrun.footballleague.dagger.league.detail.DetailLeagueViewModelModule
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import id.shobrun.footballleague.views.LeagueActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent
    @LeagueScope
    @ContributesAndroidInjector
    abstract fun leagueActivity() : LeagueActivity

    @DetailLeagueScope
    @ContributesAndroidInjector (modules = [LeagueFragmentModule::class,DetailLeagueModule::class,DetailLeagueViewModelModule::class])
    abstract fun detailLeagueActivity (): DetailLeagueActivity
}