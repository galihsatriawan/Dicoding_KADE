package id.shobrun.footballleague.di.league.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueViewModel

@Module
abstract class DetailLeagueViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailLeagueViewModel::class)
    abstract fun bindDetailLeagueViewModel(viewModel: DetailLeagueViewModel) : ViewModel
}