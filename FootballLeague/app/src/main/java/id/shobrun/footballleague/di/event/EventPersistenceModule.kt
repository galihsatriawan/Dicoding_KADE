package id.shobrun.footballleague.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.room.AppDatabase

@Module
class EventPersistenceModule {
    @Provides
    fun provideEventDao(database: AppDatabase) = database.eventDao()
}