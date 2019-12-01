package id.shobrun.footballleague.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.di.league.LeagueNetworkModule
import id.shobrun.footballleague.di.league.LeaguePersistenceModule
import id.shobrun.footballleague.di.league.LeagueRepositoryModule
import id.shobrun.footballleague.di.league.list.LeagueFragmentModule
import id.shobrun.footballleague.di.league.list.LeagueScope
import id.shobrun.footballleague.di.league.detail.DetailLeagueModule
import id.shobrun.footballleague.di.league.detail.DetailLeagueScope
import id.shobrun.footballleague.di.league.detail.DetailLeagueViewModelModule
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import id.shobrun.footballleague.views.LeagueActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent
    @LeagueScope
    @ContributesAndroidInjector
    abstract fun leagueActivity() : LeagueActivity

    @DetailLeagueScope
    @ContributesAndroidInjector (modules = [
        DetailLeagueModule::class,
        DetailLeagueViewModelModule::class,
        LeagueRepositoryModule::class,
        LeagueNetworkModule::class,
        LeaguePersistenceModule::class])
    abstract fun detailLeagueActivity (): DetailLeagueActivity
}