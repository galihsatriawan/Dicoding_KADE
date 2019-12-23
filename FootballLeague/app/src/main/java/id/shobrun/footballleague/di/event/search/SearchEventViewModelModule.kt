package id.shobrun.footballleague.di.event.search

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.events.search.SearchEventsViewModel

@Module
abstract class SearchEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchEventsViewModel::class)
    abstract fun provideSearchEventsViewModel(searchEventsViewModel: SearchEventsViewModel) : ViewModel

}