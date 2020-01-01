package id.shobrun.footballleague.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.di.event.EventNetworkModule
import id.shobrun.footballleague.di.event.EventPersistenceModule
import id.shobrun.footballleague.di.event.EventRepositoryModule
import id.shobrun.footballleague.di.event.favorite.next.FavoriteNextEventModule
import id.shobrun.footballleague.di.event.favorite.next.FavoriteNextEventViewModelModule
import id.shobrun.footballleague.di.event.favorite.prev.FavoritePrevEventModule
import id.shobrun.footballleague.di.event.favorite.prev.FavoritePrevEventViewModelModule
import id.shobrun.footballleague.di.event.next.NextEventFragmentModule
import id.shobrun.footballleague.di.event.next.NextEventViewModelModule
import id.shobrun.footballleague.di.event.prev.PrevEventFragmentModule
import id.shobrun.footballleague.di.event.prev.PrevEventViewModelModule
import id.shobrun.footballleague.di.league.LeagueNetworkModule
import id.shobrun.footballleague.di.league.LeaguePersistenceModule
import id.shobrun.footballleague.di.league.LeagueRepositoryModule
import id.shobrun.footballleague.di.league.list.LeagueFragmentModule
import id.shobrun.footballleague.di.league.list.LeagueFragmentViewModelModule
import id.shobrun.footballleague.di.league.list.LeagueScope
import id.shobrun.footballleague.di.search.event.SearchEventModule
import id.shobrun.footballleague.di.search.event.SearchEventViewModelModule
import id.shobrun.footballleague.di.search.team.SearchTeamsModule
import id.shobrun.footballleague.di.search.team.SearchTeamsViewModelModule
import id.shobrun.footballleague.di.team.TeamNetworkModule
import id.shobrun.footballleague.di.team.TeamPersistenceModule
import id.shobrun.footballleague.di.team.TeamRepositoryModule
import id.shobrun.footballleague.di.team.favorite.FavoriteTeamModule
import id.shobrun.footballleague.di.team.favorite.FavoriteTeamViewModelModule
import id.shobrun.footballleague.di.team.list.TeamsFragmentModule
import id.shobrun.footballleague.di.team.list.TeamsFragmentViewModelModule
import id.shobrun.footballleague.di.teamrecord.TeamRecordNetworkModule
import id.shobrun.footballleague.di.teamrecord.TeamRecordPersistenceModule
import id.shobrun.footballleague.di.teamrecord.TeamRecordRepositoryModule
import id.shobrun.footballleague.di.teamrecord.standing.StandingFragmentModule
import id.shobrun.footballleague.di.teamrecord.standing.StandingViewModelModule
import id.shobrun.footballleague.repository.TeamRecordRepository
import id.shobrun.footballleague.ui.events.favorite.next.FavoriteNextEventFragment
import id.shobrun.footballleague.ui.events.favorite.previous.FavoritePreviousEventFragment
import id.shobrun.footballleague.ui.events.next.NextEventFragment
import id.shobrun.footballleague.ui.events.previous.PreviousEventFragment
import id.shobrun.footballleague.ui.leagues.list.FootballLeaguesFragment
import id.shobrun.footballleague.ui.leagues.standing.StandingFragment
import id.shobrun.footballleague.ui.leagues.team.TeamsFragment
import id.shobrun.footballleague.ui.leagues.team.favorite.FavoriteTeamsFragment
import id.shobrun.footballleague.ui.leagues.team.favorite.FavoriteTeamsViewModel
import id.shobrun.footballleague.ui.search.event.SearchEventsFragment
import id.shobrun.footballleague.ui.search.team.SearchTeamsFragment

@Module
abstract class FragmentBuildersModule {

    @LeagueScope
    @ContributesAndroidInjector(
        modules = [
            LeagueFragmentModule::class,
            LeagueFragmentViewModelModule::class,
            LeagueRepositoryModule::class,
            LeagueNetworkModule::class,
            LeaguePersistenceModule::class]
    )
    abstract fun injectLeagueFragment(): FootballLeaguesFragment

    @ContributesAndroidInjector(
        modules = [
            NextEventFragmentModule::class,
            NextEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class
        ]
    )
    abstract fun injectNextEventFragment(): NextEventFragment

    @ContributesAndroidInjector(
        modules = [
            PrevEventFragmentModule::class,
            PrevEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class
        ]
    )
    abstract fun injectPrevEventFragment(): PreviousEventFragment

    @ContributesAndroidInjector(
        modules = [
            FavoritePrevEventModule::class,
            FavoritePrevEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class
        ]
    )
    abstract fun injectFavoritePrevEventFragment(): FavoritePreviousEventFragment

    @ContributesAndroidInjector(
        modules = [
            FavoriteNextEventModule::class,
            FavoriteNextEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class
        ]
    )
    abstract fun injectFavoriteNextEventFragment(): FavoriteNextEventFragment

    @ContributesAndroidInjector(
        modules = [
            FavoriteTeamModule::class,
            FavoriteTeamViewModelModule::class,
            TeamRepositoryModule::class,
            TeamNetworkModule::class,
            TeamPersistenceModule::class
        ]
    )
    abstract fun injectFavoriteTeamFragment() : FavoriteTeamsFragment

    @ContributesAndroidInjector(
        modules = [
            TeamsFragmentModule::class,
            TeamsFragmentViewModelModule::class,
            TeamRepositoryModule::class,
            TeamNetworkModule::class,
            TeamPersistenceModule::class
        ]
    )
    abstract fun injectTeamsFragment() : TeamsFragment

    @ContributesAndroidInjector(
        modules = [
            StandingFragmentModule::class,
            StandingViewModelModule::class,
            TeamRecordRepositoryModule::class,
            TeamRecordNetworkModule::class,
            TeamRecordPersistenceModule::class
        ]
    )
    abstract fun injectStandingFragment() : StandingFragment

    @ContributesAndroidInjector(
        modules = [
            SearchTeamsModule::class,
            SearchTeamsViewModelModule::class,
            TeamRepositoryModule::class,
            TeamNetworkModule::class,
            TeamPersistenceModule::class
        ]
    )
    abstract fun injectSearchTeamsFragment() : SearchTeamsFragment

    @ContributesAndroidInjector(
        modules = [
            SearchEventModule::class,
            SearchEventViewModelModule::class,
            EventRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class
        ]
    )
    abstract fun injectSearchEventsFragment() : SearchEventsFragment

}