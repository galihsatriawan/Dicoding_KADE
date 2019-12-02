package id.shobrun.footballleague.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.repositories.EventRepository
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.room.EventDao

@Module
class EventRepositoryModule {
    @Provides
    fun provideEventRepository(service : EventApi, local : EventDao) : EventRepository{
        return EventRepository(service,local)
    }
}