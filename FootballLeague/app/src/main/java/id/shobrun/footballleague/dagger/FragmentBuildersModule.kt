package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.dagger.league.list.LeagueFragmentModule
import id.shobrun.footballleague.dagger.league.list.LeagueFragmentViewModelModule
import id.shobrun.footballleague.dagger.league.list.LeagueScope
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment

@Module
abstract class FragmentBuildersModule {

    @LeagueScope
    @ContributesAndroidInjector(modules = [LeagueFragmentModule::class, LeagueFragmentViewModelModule::class])
    abstract fun injectLeagueFragment() : FootballLeaguesFragment
}