package id.shobrun.footballleague.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.di.event.EventNetworkModule
import id.shobrun.footballleague.di.event.EventPersistenceModule
import id.shobrun.footballleague.di.event.EventRepositoryModule
import id.shobrun.footballleague.di.event.detail.DetailEventModule
import id.shobrun.footballleague.di.event.detail.DetailEventViewModelModule
import id.shobrun.footballleague.di.league.LeagueNetworkModule
import id.shobrun.footballleague.di.league.LeaguePersistenceModule
import id.shobrun.footballleague.di.league.LeagueRepositoryModule
import id.shobrun.footballleague.di.league.detail.DetailLeagueModule
import id.shobrun.footballleague.di.league.detail.DetailLeagueScope
import id.shobrun.footballleague.di.league.detail.DetailLeagueViewModelModule
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent

    @DetailLeagueScope
    @ContributesAndroidInjector (modules = [
        DetailLeagueModule::class,
        DetailLeagueViewModelModule::class,
        LeagueRepositoryModule::class,
        LeagueNetworkModule::class,
        LeaguePersistenceModule::class])
    abstract fun detailLeagueActivity (): DetailLeagueActivity

    @ContributesAndroidInjector (modules = [
        DetailEventModule::class,
        DetailEventViewModelModule::class,
        EventRepositoryModule::class,
        EventNetworkModule::class,
        EventPersistenceModule::class
    ])
    abstract fun detailEventActivity() : DetailEventActivity
}