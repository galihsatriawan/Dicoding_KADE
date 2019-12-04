package id.shobrun.footballleague.di.league

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.LeagueApi
import retrofit2.Retrofit

@Module
class LeagueNetworkModule {
    /**
     * Network
     */

    @Provides
    fun provideLeagueApi(retrofit: Retrofit) : LeagueApi {
        return retrofit.create(LeagueApi::class.java)
    }
}