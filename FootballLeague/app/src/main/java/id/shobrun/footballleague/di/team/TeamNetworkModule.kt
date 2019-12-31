package id.shobrun.footballleague.di.team

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.TeamApi
import retrofit2.Retrofit

@Module
class TeamNetworkModule {
    @Provides
    fun provideTeamApi(retrofit: Retrofit): TeamApi {
        return retrofit.create(TeamApi::class.java)
    }
}