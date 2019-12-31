package id.shobrun.footballleague.di.event.favorite.prev

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.events.favorite.previous.FavoritePreviousEventViewModel

@Module
abstract class FavoritePrevEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoritePreviousEventViewModel::class)
    abstract fun bindFavoritePrevEventViewModel(favoritePreviousEventViewModel: FavoritePreviousEventViewModel): ViewModel
}