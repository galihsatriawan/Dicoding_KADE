package id.shobrun.footballleague.repository

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.mapper.EventResponseMapper
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.room.AppDatabase
import id.shobrun.footballleague.room.EventDao
import timber.log.Timber
import javax.inject.Inject

class EventRepository @Inject constructor(val webservice : EventApi, val eventDao : EventDao) : Repository{
    fun getDetailEvent(idEvent : Int) : LiveData<Resource<Event>>{
        return object : NetworkBoundRepository<Event, EventsResponse , EventResponseMapper>(){
            override fun saveFetchData(items: EventsResponse) {
                val item = items.events[0]
                eventDao.insert(item)
            }

            override fun shouldFetch(data: Event?): Boolean {
                return data == null || data?.leagueName.isEmpty()
            }

            override fun loadFromDb(): LiveData<Event> {
                return eventDao.getEventById(idEvent)
            }

            override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
                return webservice.getDetailEvents(idEvent)
            }

            override fun mapper(): EventResponseMapper {
                return EventResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG Fetch Detail Event Failed : $message")
            }
        }.asLiveData()
    }
    fun getPreviousEvents(idLeague: Int) : LiveData<Resource<List<Event>>>{
        return object : NetworkBoundRepository<List<Event>,EventsResponse , EventResponseMapper>(){
            override fun saveFetchData(items: EventsResponse) {
                val events = items.events
                for (e in events){
                    e.tags = AppDatabase.TAG_PAST_MATCH
                }
                eventDao.insertEvents(events)
            }

            override fun shouldFetch(data: List<Event>?): Boolean {
                return data?.isNullOrEmpty()?: true
            }

            override fun loadFromDb(): LiveData<List<Event>> {
                return eventDao.getPastEvents(idLeague)
            }

            override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
                return webservice.getPastEvents(idLeague)
            }

            override fun mapper(): EventResponseMapper {
                return EventResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG fetch Past Event Failed : $message")
            }
        }.asLiveData()
    }
    fun getNextEvents(idLeague : Int) : LiveData<Resource<List<Event>>>{
        return object : NetworkBoundRepository<List<Event>, EventsResponse, EventResponseMapper>(){
            override fun saveFetchData(items: EventsResponse) {
                val events = items.events
                for (e in events){
                    e.tags = AppDatabase.TAG_NEXT_MATCH
                }
                eventDao.insertEvents(events)
            }

            override fun shouldFetch(data: List<Event>?): Boolean {
                return data?.isNullOrEmpty()?: true
            }

            override fun loadFromDb(): LiveData<List<Event>> {
                return eventDao.getNextEvents(idLeague)
            }

            override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
                return webservice.getNextEvents(idLeague)
            }

            override fun mapper(): EventResponseMapper {
                return EventResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG Fetch failed $message")
            }

        }.asLiveData()
    }

}