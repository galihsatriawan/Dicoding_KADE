package id.shobrun.footballleague.dagger.league

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.auth.AuthApi
import retrofit2.Retrofit
import retrofit2.create


@Module
class LeagueFragmentModule {
    @Provides
    fun getInstanceRetrofit(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}