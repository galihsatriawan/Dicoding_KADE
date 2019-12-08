package id.shobrun.footballleague.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.mapper.EventResponseMapper
import id.shobrun.footballleague.mapper.EventSearchResponseMapper
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventSearchResponse
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.repository.utils.IEventLocalDB
import id.shobrun.footballleague.room.AppDatabase
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.utils.AbsentLiveData
import org.jetbrains.anko.design.snackbar
import timber.log.Timber
import javax.inject.Inject

class EventRepository @Inject constructor(val webservice : EventApi, val eventDao : EventDao) : Repository, IEventLocalDB{
    companion object{
        val TAG = EventRepository.javaClass.name
    }
    /**
     * Network
     */
    fun getDetailEvent(idEvent : Int) : LiveData<Resource<Event>>{
        return object : NetworkBoundRepository<Event, EventsResponse , EventResponseMapper>(){
            override fun saveFetchData(items: EventsResponse) {
                val item = items.events[0]

                eventDao.insert(item)
            }

            override fun shouldFetch(data: Event?): Boolean {
                return data == null || data.eventName.isEmpty()
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
                if(events!=null){
                    for (e in events){
                        e.tags = AppDatabase.TAG_PAST_MATCH
                        e.isFavorite = 0
                        Timber.d("$TAG favorite Prev: ${e.isFavorite}")
                    }
                    eventDao.insertEvents(events)
                }

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
                if(events!=null){
                    for (e in events){
                        e.tags = AppDatabase.TAG_NEXT_MATCH
                        e.isFavorite = 0
                        Timber.d("$TAG favorite : ${e.isFavorite}")
                    }
                    eventDao.insertEvents(events)
                }

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
    fun getSearchEvent(q : String) : LiveData<Resource<List<Event>>>{
        return object : NetworkBoundRepository<List<Event>, EventSearchResponse, EventSearchResponseMapper>(){
            override fun saveFetchData(items: EventSearchResponse) {
                val events = items.event
                val eventSoccer : ArrayList<Event> = ArrayList()
                if(!events.isNullOrEmpty()){
                    for(e in events){
                        e.tags = "[qry=$q]"
                        if(e.sportCategory.equals("Soccer"))
                            eventSoccer.add(e)
                    }
                    eventDao.insertEvents(eventSoccer)
                }

            }

            override fun shouldFetch(data: List<Event>?): Boolean {
                return data?.isNullOrEmpty()?:true
            }

            override fun loadFromDb(): LiveData<List<Event>> {
                val qry = "[qry=${q}]"
                return eventDao.getSearchEvent(qry)
            }

            override fun fetchService(): LiveData<ApiResponse<EventSearchResponse>> {
                return webservice.getSearchEvents(q)
            }

            override fun mapper(): EventSearchResponseMapper {
                return EventSearchResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG fetch failed Query Event : $message")
            }
        }.asLiveData()
    }

    /**
     * Local Database
     */
    override fun insertEventToDb(event: Event){
        try {
            eventDao.insert(event)
        }catch (e:Throwable){
            Timber.d("$TAG ${e.printStackTrace()}")
        }

    }

    override fun getAllFavoriteNextEventInDb(idLeague: Int): LiveData<List<Event>> {
        val events = eventDao.getFavoriteNextEvents(idLeague,1)
        return events
    }

    override fun getAllFavoritePrevEventInDb(idLeague: Int): LiveData<List<Event>> {
        return eventDao.getFavoritePastEvents(idLeague,1)
    }

    override fun getEventByIdInDb(idEvent: Int) : LiveData<Event>{
        return eventDao.getEventById(idEvent)
    }

    override fun updateEventInDb(event : Event) : Int{
        return eventDao.update(event)
    }
}