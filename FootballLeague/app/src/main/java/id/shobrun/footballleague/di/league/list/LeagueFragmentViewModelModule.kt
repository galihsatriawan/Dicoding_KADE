package id.shobrun.footballleague.di.league.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey

import id.shobrun.footballleague.ui.leagues.list.FootballLeaguesViewModel

@Module
abstract class LeagueFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FootballLeaguesViewModel::class)
    abstract fun bindLeaguesViewModel(footballLeaguesViewModel: FootballLeaguesViewModel) : ViewModel
}