package id.shobrun.footballleague.dagger.league.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.dagger.ViewModelKey
import id.shobrun.footballleague.views.leagues.DetailLeagueViewModel

@Module
abstract class DetailLeagueViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailLeagueViewModel::class)
    abstract fun bindDetailLeagueViewModel(viewModel: DetailLeagueViewModel) : ViewModel
}