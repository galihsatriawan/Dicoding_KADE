package id.shobrun.footballleague.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.di.event.EventNetworkModule
import id.shobrun.footballleague.di.event.EventPersistenceModule
import id.shobrun.footballleague.di.event.EventRepositoryModule
import id.shobrun.footballleague.di.event.detail.DetailEventModule
import id.shobrun.footballleague.di.event.detail.DetailEventViewModelModule
import id.shobrun.footballleague.di.search.event.SearchEventModule
import id.shobrun.footballleague.di.search.event.SearchEventViewModelModule
import id.shobrun.footballleague.di.league.LeagueNetworkModule
import id.shobrun.footballleague.di.league.LeaguePersistenceModule
import id.shobrun.footballleague.di.league.LeagueRepositoryModule
import id.shobrun.footballleague.di.league.detail.DetailLeagueModule
import id.shobrun.footballleague.di.league.detail.DetailLeagueScope
import id.shobrun.footballleague.di.league.detail.DetailLeagueViewModelModule
import id.shobrun.footballleague.di.team.TeamNetworkModule
import id.shobrun.footballleague.di.team.TeamPersistenceModule
import id.shobrun.footballleague.di.team.TeamRepositoryModule
import id.shobrun.footballleague.di.team.detail.DetailTeamModule
import id.shobrun.footballleague.di.team.detail.DetailTeamViewModelModule
import id.shobrun.footballleague.ui.events.detail.DetailEventActivity
import id.shobrun.footballleague.ui.search.SearchActivity
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueActivity
import id.shobrun.footballleague.ui.leagues.team.detail.DetailTeamActivity

@Module
abstract class ActivityBuildersModule {
    // Create Simple SubComponent

    @DetailLeagueScope
    @ContributesAndroidInjector(
        modules = [
            DetailLeagueModule::class,
            DetailLeagueViewModelModule::class,
            LeagueRepositoryModule::class,
            LeagueNetworkModule::class,
            LeaguePersistenceModule::class]
    )
    abstract fun detailLeagueActivity(): DetailLeagueActivity

    @ContributesAndroidInjector(
        modules = [
            DetailEventModule::class,
            DetailEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            TeamNetworkModule::class,
            TeamPersistenceModule::class,
            TeamRepositoryModule::class
        ]
    )
    abstract fun detailEventActivity(): DetailEventActivity


    @ContributesAndroidInjector(
        modules = [
            DetailTeamModule::class,
            DetailTeamViewModelModule::class,
            TeamNetworkModule::class,
            TeamPersistenceModule::class,
            TeamRepositoryModule::class
        ]
    )
    abstract fun detailTeamActivity(): DetailTeamActivity

    @ContributesAndroidInjector(
        modules = [
            SearchEventModule::class,
            SearchEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class
        ]
    )
    abstract fun searchEventActivity(): SearchActivity
}