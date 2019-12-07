package id.shobrun.footballleague.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.database.MyDatabaseOpenHelper
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.room.EventDao

@Module
class EventRepositoryModule {
    @Provides
    fun provideEventRepository(service : EventApi, local : EventDao, dbSqlite : MyDatabaseOpenHelper) : EventRepository{
        return EventRepository(service,local,dbSqlite)
    }
}