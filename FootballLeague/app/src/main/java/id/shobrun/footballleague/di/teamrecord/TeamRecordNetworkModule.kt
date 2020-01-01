package id.shobrun.footballleague.di.teamrecord

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.TeamRecordApi
import retrofit2.Retrofit

@Module
class TeamRecordNetworkModule {
    @Provides
    fun provideTeamRecordApi(retrofit: Retrofit) = retrofit.create(TeamRecordApi::class.java)
}