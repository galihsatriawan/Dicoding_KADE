package id.shobrun.footballleague.di.event.prev

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.events.previous.PreviousEventViewModel

@Module
abstract class PrevEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PreviousEventViewModel::class)
    abstract fun bindPreviousEventViewModel(previousEventViewModel: PreviousEventViewModel) : ViewModel
}