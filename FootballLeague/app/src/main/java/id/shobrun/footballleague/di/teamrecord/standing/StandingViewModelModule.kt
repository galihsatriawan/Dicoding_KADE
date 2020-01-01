package id.shobrun.footballleague.di.teamrecord.standing

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.leagues.standing.StandingViewModel

@Module
abstract class StandingViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(StandingViewModel::class)
    abstract fun bindStandingViewModel(viewModel: StandingViewModel): ViewModel
}