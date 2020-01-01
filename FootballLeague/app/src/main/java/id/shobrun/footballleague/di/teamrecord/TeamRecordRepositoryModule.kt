package id.shobrun.footballleague.di.teamrecord

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.TeamRecordApi
import id.shobrun.footballleague.repository.TeamRecordRepository
import id.shobrun.footballleague.repository.utils.IEventLocalDB
import id.shobrun.footballleague.room.TeamRecordDao

@Module
class TeamRecordRepositoryModule {
    @Provides
    fun provideTeamRecordRepository(appExecutors: AppExecutors,apiService: TeamRecordApi,localDB: TeamRecordDao) = TeamRecordRepository(appExecutors,apiService,localDB)
}