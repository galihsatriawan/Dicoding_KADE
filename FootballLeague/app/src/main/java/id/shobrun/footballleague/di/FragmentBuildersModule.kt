package id.shobrun.footballleague.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.di.event.EventNetworkModule
import id.shobrun.footballleague.di.event.EventPersistenceModule
import id.shobrun.footballleague.di.event.EventRepositoryModule
import id.shobrun.footballleague.di.event.next.NextEventFragmentModule
import id.shobrun.footballleague.di.event.next.NextEventViewModelModule
import id.shobrun.footballleague.di.league.LeagueNetworkModule
import id.shobrun.footballleague.di.league.LeaguePersistenceModule
import id.shobrun.footballleague.di.league.LeagueRepositoryModule
import id.shobrun.footballleague.di.league.list.LeagueFragmentModule
import id.shobrun.footballleague.di.league.list.LeagueFragmentViewModelModule
import id.shobrun.footballleague.di.league.list.LeagueScope
import id.shobrun.footballleague.ui.events.next.NextEventFragment
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment

@Module
abstract class FragmentBuildersModule {

    @LeagueScope
    @ContributesAndroidInjector(modules = [
        LeagueFragmentModule::class,
        LeagueFragmentViewModelModule::class,
        LeagueRepositoryModule::class,
        LeagueNetworkModule::class,
        LeaguePersistenceModule::class])
    abstract fun injectLeagueFragment() : FootballLeaguesFragment

    @ContributesAndroidInjector(modules = [
        NextEventFragmentModule::class,
        NextEventViewModelModule::class,
        EventRepositoryModule::class,
        EventNetworkModule::class,
        EventPersistenceModule::class
    ])
    abstract fun injectNextEventFragment() : NextEventFragment
}