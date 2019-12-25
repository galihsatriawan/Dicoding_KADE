package id.shobrun.footballleague.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.room.EventDao

@Module
class EventRepositoryModule {
    @Provides
    fun provideEventRepository(appExecutors: AppExecutors,service : EventApi, local : EventDao) : EventRepository{
        return EventRepository(appExecutors,service,local)
    }
}