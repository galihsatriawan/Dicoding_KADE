package id.shobrun.footballleague.di.team.favorite

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.leagues.team.favorite.FavoriteTeamsViewModel

@Module
abstract class FavoriteTeamViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteTeamsViewModel::class)
    abstract fun bindFavoriteTeamViewModel(viewModel: FavoriteTeamsViewModel): ViewModel
}