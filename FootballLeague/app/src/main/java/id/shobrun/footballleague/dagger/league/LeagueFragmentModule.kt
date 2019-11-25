package id.shobrun.footballleague.dagger.league

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.api.auth.AuthApi
import id.shobrun.footballleague.repositories.LeagueRepository
import retrofit2.Retrofit
import retrofit2.create


@Module
class LeagueFragmentModule {

    @Provides
    fun getInstanceRetrofit(retrofit: Retrofit) : LeagueApi{
        return retrofit.create(LeagueApi::class.java)
    }
    @Provides
    fun getInstanceRepository(leagueApi: LeagueApi,application: Application) : LeagueRepository{
        return LeagueRepository(leagueApi,application)
    }
}