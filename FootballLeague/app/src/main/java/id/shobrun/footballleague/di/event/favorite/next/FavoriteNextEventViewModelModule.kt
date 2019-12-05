package id.shobrun.footballleague.di.event.favorite.next

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.di.ViewModelKey
import id.shobrun.footballleague.ui.events.favorite.next.FavoriteNextEventViewModel

@Module
abstract class FavoriteNextEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteNextEventViewModel::class)
    abstract fun bindFavoriteNextEventViewModel(favoriteNextEventViewModel: FavoriteNextEventViewModel) : ViewModel
}