package id.shobrun.footballleague.di.search.event

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.search.event.SearchEventsViewModel

@Module
abstract class SearchEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchEventsViewModel::class)
    abstract fun provideSearchEventsViewModel(searchEventsViewModel: SearchEventsViewModel): ViewModel

}