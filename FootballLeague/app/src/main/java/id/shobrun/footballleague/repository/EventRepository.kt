package id.shobrun.footballleague.repository

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventSearchResponse
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.repository.utils.IEventLocalDB
import id.shobrun.footballleague.room.AppDatabase
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.testing.OpenForTesting
import id.shobrun.footballleague.utils.wrapEspressoIdlingResource
import timber.log.Timber
import javax.inject.Inject

@OpenForTesting
class EventRepository @Inject constructor(
    var appExecutors: AppExecutors,
    val apiService: EventApi,
    val localDB: EventDao
) : Repository, IEventLocalDB {
    companion object {
        val TAG = EventRepository.javaClass.name
    }

    /**
     * Network
     */
    fun getDetailEvent(idEvent: Int): LiveData<Resource<Event>> {

        return object :
            NetworkBoundRepository<Event, EventsResponse>(appExecutors) {
            override fun saveFetchData(items: EventsResponse) {
                val item = items.events[0]

                localDB.insert(item)
            }

            override fun shouldFetch(data: Event?): Boolean {
                return data == null || data.eventName.isEmpty()
            }

            override fun loadFromDb(): LiveData<Event> {
                return localDB.getEventById(idEvent)
            }

            override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
                return apiService.getDetailEvents(idEvent)
            }


            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG Fetch Detail Event Failed : $message")
            }
        }.asLiveData()
    }

    fun getPreviousEvents(idLeague: Int): LiveData<Resource<List<Event>>> {
        return object :
            NetworkBoundRepository<List<Event>, EventsResponse>(appExecutors) {
            override fun saveFetchData(items: EventsResponse) {
                val events = items.events
                if (events != null) {
                    for (e in events) {
                        e.tags = AppDatabase.TAG_PAST_MATCH
                        e.isFavorite = 0
                        Timber.d("$TAG favorite Prev: ${e.isFavorite}")
                    }
                    localDB.insertEvents(events)
                }

            }

            override fun shouldFetch(data: List<Event>?): Boolean {
                return data?.isNullOrEmpty() ?: true
            }

            override fun loadFromDb(): LiveData<List<Event>> {
                return localDB.getPastEvents(idLeague)
            }

            override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
                return apiService.getPastEvents(idLeague)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG fetch Past Event Failed : $message")
            }
        }.asLiveData()
    }

    fun getNextEvents(idLeague: Int): LiveData<Resource<List<Event>>> {

        return object :
            NetworkBoundRepository<List<Event>, EventsResponse>(appExecutors) {
            override fun saveFetchData(items: EventsResponse) {
                val events = items.events
                if (events != null) {
                    for (e in events) {
                        e.tags = AppDatabase.TAG_NEXT_MATCH
                        e.isFavorite = 0
                        Timber.d("$TAG favorite : ${e.isFavorite}")
                    }
                    localDB.insertEvents(events)
                }

            }

            override fun shouldFetch(data: List<Event>?): Boolean {
                return data?.isNullOrEmpty() ?: true
            }

            override fun loadFromDb(): LiveData<List<Event>> {
                return localDB.getNextEvents(idLeague)
            }

            override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
                return apiService.getNextEvents(idLeague)
            }


            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG Fetch failed $message")
            }

        }.asLiveData()
    }

    fun getSearchEvent(q: String): LiveData<Resource<List<Event>>> {
        wrapEspressoIdlingResource {
            return object :
                NetworkBoundRepository<List<Event>, EventSearchResponse>(
                    appExecutors
                ) {
                override fun saveFetchData(items: EventSearchResponse) {
                    val events = items.event
                    val eventSoccer: ArrayList<Event> = ArrayList()
                    if (!events.isNullOrEmpty()) {
                        for (e in events) {
                            e.tags = "[qry=$q]"
                            if (e.sportCategory.equals("Soccer"))
                                eventSoccer.add(e)
                        }
                        localDB.insertEvents(eventSoccer)
                    }
                }

                override fun shouldFetch(data: List<Event>?): Boolean {
                    return data?.isNullOrEmpty() ?: true
                }

                override fun loadFromDb(): LiveData<List<Event>> {
                    val qry = "%[qry=${q}]%"
                    return localDB.getSearchEvent(qry)
                }

                override fun fetchService(): LiveData<ApiResponse<EventSearchResponse>> {
                    return apiService.getSearchEvents(q)
                }

                override fun onFetchFailed(message: String?) {
                    Timber.d("$TAG fetch failed Query Event : $message")
                }
            }.asLiveData()
        }
    }

    /**
     * Local Database
     */
    override fun insertEventToDb(event: Event) {
        try {
            localDB.insert(event)
        } catch (e: Throwable) {
            Timber.d("$TAG ${e.printStackTrace()}")
        }

    }

    override fun getAllFavoriteNextEventInDb(idLeague: Int): LiveData<List<Event>> {
        val events = localDB.getFavoriteNextEvents(idLeague, 1)
        return events
    }

    override fun getAllFavoritePrevEventInDb(idLeague: Int): LiveData<List<Event>> {
        return localDB.getFavoritePastEvents(idLeague, 1)
    }

    override fun getEventByIdInDb(idEvent: Int): LiveData<Event> {
        return localDB.getEventById(idEvent)
    }

    override fun updateEventInDb(event: Event): Int {
        return localDB.update(event)
    }
}