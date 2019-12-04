package id.shobrun.footballleague.di.team

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.TeamApi
import id.shobrun.footballleague.repository.TeamRepository
import id.shobrun.footballleague.room.TeamDao

@Module
class TeamRepositoryModule {
    @Provides
    fun provideTeamRepository(teamApi: TeamApi,teamDao: TeamDao) = TeamRepository(teamApi,teamDao)
}