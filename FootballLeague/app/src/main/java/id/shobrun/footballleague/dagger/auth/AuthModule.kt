package id.shobrun.footballleague.dagger.auth

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.auth.AuthApi
import retrofit2.Retrofit

@Module
class AuthModule{
    @Provides
    internal fun provideAuthApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)
}