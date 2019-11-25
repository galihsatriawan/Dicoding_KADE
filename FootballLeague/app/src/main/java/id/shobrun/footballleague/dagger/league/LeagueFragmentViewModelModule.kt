package id.shobrun.footballleague.dagger.league

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.dagger.ViewModelKey

import id.shobrun.footballleague.views.leagues.fragments.FootballLeaguesViewModel

@Module
abstract class LeagueFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FootballLeaguesViewModel::class)
    abstract fun bindLeaguesViewModel(footballLeaguesViewModel: FootballLeaguesViewModel) : ViewModel
}