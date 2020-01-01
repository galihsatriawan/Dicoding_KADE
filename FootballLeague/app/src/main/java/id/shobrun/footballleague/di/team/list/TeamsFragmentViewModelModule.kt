package id.shobrun.footballleague.di.team.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.leagues.team.TeamsViewModel

@Module
abstract class TeamsFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TeamsViewModel::class)
    abstract fun bindTeamsViewModel(viewModel: TeamsViewModel) : ViewModel
}