package id.shobrun.footballleague.di.team.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.leagues.team.detail.DetailTeamViewModel

@Module
abstract class DetailTeamViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailTeamViewModel::class)
    abstract fun bindDetailTeamViewModel(viewModel: DetailTeamViewModel): ViewModel
}