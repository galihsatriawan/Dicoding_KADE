package id.shobrun.footballleague.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.footballleague.dagger.league.LeagueFragmentModule
import id.shobrun.footballleague.views.LeagueActivity
import id.shobrun.footballleague.views.leagues.DetailLeaguesActivity
import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesFragment

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector( modules = [ LeagueFragmentModule::class ] )
    abstract fun injectMainLeagueActivity() : LeagueActivity

}