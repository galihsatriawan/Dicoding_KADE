package id.shobrun.footballleague.di.event.next

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.events.next.NextEventViewModel

@Module
abstract class NextEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NextEventViewModel::class)
    abstract fun bindNextEventViewModel(nextEventViewModel: NextEventViewModel) : ViewModel
}