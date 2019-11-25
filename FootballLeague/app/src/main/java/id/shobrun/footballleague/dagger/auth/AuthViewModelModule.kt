package id.shobrun.footballleague.dagger.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.footballleague.dagger.ViewModelKey
import id.shobrun.footballleague.ui.auth.AuthViewModel

@Module
abstract class AuthViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthViewModel(authViewModel: AuthViewModel) : ViewModel
}