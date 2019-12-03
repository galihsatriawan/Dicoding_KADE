package id.shobrun.footballleague.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.events.detail.DetailEventViewModel

@Module
abstract class DetailEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailEventViewModel::class)
    abstract fun bindDetailEventViewModel(detailEventViewModel: DetailEventViewModel) : ViewModel
}