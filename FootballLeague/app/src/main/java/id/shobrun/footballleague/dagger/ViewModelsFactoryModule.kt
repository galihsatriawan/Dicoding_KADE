package id.shobrun.footballleague.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.factory.AppViewModelsFactory
import id.shobrun.footballleague.ui.auth.AuthViewModel

@Module
abstract class ViewModelsFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory:AppViewModelsFactory) : ViewModelProvider.Factory
}