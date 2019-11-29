package id.shobrun.footballleague.dagger.league.list

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.repositories.remote.LeagueRemoteDataSource
import retrofit2.Retrofit


@Module
class LeagueFragmentModule {

    @Provides
    fun getInstanceRetrofit(retrofit: Retrofit) : LeagueApi{
        return retrofit.create(LeagueApi::class.java)
    }
    @Provides
    fun getInstanceRepository(remote: LeagueRemoteDataSource,application: Application) : LeagueRepository{
        return LeagueRepository(remote,application)
    }
    @Provides
    fun getInstanceRemoteDataSource(leagueApi: LeagueApi) : LeagueRemoteDataSource{
        return LeagueRemoteDataSource(leagueApi)
    }
}