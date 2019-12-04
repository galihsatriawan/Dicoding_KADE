package id.shobrun.footballleague.di.team

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.room.AppDatabase

@Module
class TeamPersistenceModule {
    @Provides
    fun provideTeamDao(database: AppDatabase) = database.teamDao()
}