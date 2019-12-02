package id.shobrun.footballleague.repositories

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.mapper.EventResponseMapper
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.room.EventDao
import org.jetbrains.anko.design.snackbar
import timber.log.Timber
import javax.inject.Inject

class EventRepository @Inject constructor(val webservice : EventApi, val eventDao : EventDao) : Repository{
    fun getNextEvents(idLeague : Int) : LiveData<Resource<List<Event>>>{
        return object : NetworkBoundRepository<List<Event>, EventsResponse, EventResponseMapper>(){
            override fun saveFetchData(items: EventsResponse) {
                val events = items.events
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