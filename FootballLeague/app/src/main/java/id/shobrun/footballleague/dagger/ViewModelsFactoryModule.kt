package id.shobrun.footballleague.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import id.shobrun.footballleague.factory.AppViewModelsFactory


@Module
abstract class ViewModelsFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory:AppViewModelsFactory) : ViewModelProvider.Factory
}