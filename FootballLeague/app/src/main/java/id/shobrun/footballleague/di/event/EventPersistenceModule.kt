package id.shobrun.footballleague.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.room.AppDatabase
import id.shobrun.footballleague.room.EventDao

@Module
class EventPersistenceModule {
    @Provides
    fun provideEventDao(database: AppDatabase) = database.eventDao()
}