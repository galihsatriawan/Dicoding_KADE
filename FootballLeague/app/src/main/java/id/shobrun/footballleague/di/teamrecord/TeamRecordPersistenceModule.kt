package id.shobrun.footballleague.di.teamrecord

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.room.AppDatabase

@Module
class TeamRecordPersistenceModule {
    @Provides
    fun provideTeamRecordDao(appDatabase: AppDatabase) = appDatabase.teamRecordDao()
}