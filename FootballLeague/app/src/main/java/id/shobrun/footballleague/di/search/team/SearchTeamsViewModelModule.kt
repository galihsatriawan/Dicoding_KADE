package id.shobrun.footballleague.di.search.team

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.search.team.SearchTeamsViewModel

@Module
abstract class SearchTeamsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchTeamsViewModel::class)
    abstract fun bindSearchTeamsViewModel(viewModel: SearchTeamsViewModel): ViewModel
}