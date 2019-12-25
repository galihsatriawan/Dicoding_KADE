package id.shobrun.footballleague.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.shobrun.footballleague.api.ApiUtil.successCall
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventSearchResponse
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.utils.InstantAppExecutors
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockEvent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EventRepositoryTest {
    private lateinit var repository: EventRepository
    private val eventDao  = mock<EventDao>()
    private val service = mock<EventApi>()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init(){
        repository = EventRepository(InstantAppExecutors(),service,eventDao)
    }

    /**
     * Scenario
     * 1. Data Detail Event pertama diambil dari DB
     * 2. Hasil pertama dari Resource adalah Loading
     * 3. Kemudian akan dilakukan pengambilan data dari Rest API
     * 4. Setelah itu simpan seluruh data yang berhasil diambil ke DB
     * 5. Setelah itu value Resource akan berubah menjadi Success
     */
    @Test
    fun getDetailEvent() {
        val idEvent = 1
        val loadFromDB = MutableLiveData<Event>()
        whenever(eventDao.getEventById(idEvent)).doReturn(loadFromDB)

        val mockResponse = EventsResponse(arrayListOf(mockEvent()))
        val call = successCall(mockResponse)
        whenever(service.getDetailEvents(idEvent)).doReturn(call)

        val data = repository.getDetailEvent(idEvent)
        verify(eventDao).getEventById(idEvent)
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<Event>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        val updatedData = MutableLiveData<Event>()
        whenever(eventDao.getEventById(idEvent)).doReturn(updatedData)

        loadFromDB.postValue(null)
        verify(observer).onChanged(Resource.loading(null))
        verify(service).getDetailEvents(idEvent)
        verify(eventDao).insert(mockResponse.events[0])

        updatedData.postValue(mockResponse.events[0])
        verify(observer).onChanged(Resource.success(mockResponse.events[0],true))

    }

    @Test
    fun getPreviousEvents() {
        val idLeague = 1
        val loadFromDB = MutableLiveData<List<Event>>()
        whenever(eventDao.getPastEvents(idLeague)).doReturn(loadFromDB)

        val mockResponse = EventsResponse(arrayListOf(mockEvent()))
        val call = successCall(mockResponse)
        whenever(service.getPastEvents(idLeague)).doReturn(call)

        val data = repository.getPreviousEvents(idLeague)
        verify(eventDao).getPastEvents(idLeague)
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<List<Event>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        val updatedData = MutableLiveData<List<Event>>()
        whenever(eventDao.getPastEvents(idLeague)).doReturn(updatedData)

        loadFromDB.postValue(null)
        verify(observer).onChanged(Resource.loading(null))
        verify(service).getPastEvents(idLeague)
        verify(eventDao).insertEvents(mockResponse.events)

        updatedData.postValue(mockResponse.events)
        verify(observer).onChanged(Resource.success(mockResponse.events,true))

    }

    @Test
    fun getNextEvents() {
        val idLeague = 1
        val loadFromDB = MutableLiveData<List<Event>>()
        whenever(eventDao.getNextEvents(idLeague)).doReturn(loadFromDB)

        val mockResponse = EventsResponse(arrayListOf(mockEvent()))
        val call = successCall(mockResponse)
        whenever(service.getNextEvents(idLeague)).doReturn(call)

        val data = repository.getNextEvents(idLeague)
        verify(eventDao).getNextEvents(idLeague)
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<List<Event>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        val updatedData = MutableLiveData<List<Event>>()
        whenever(eventDao.getNextEvents(idLeague)).doReturn(updatedData)

        loadFromDB.postValue(null)
        verify(observer).onChanged(Resource.loading(null))
        verify(service).getNextEvents(idLeague)
        verify(eventDao).insertEvents(mockResponse.events)

        updatedData.postValue(mockResponse.events)
        verify(observer).onChanged(Resource.success(mockResponse.events,true))

    }

    @Test
    fun getSearchEvent() {
        val qry = "event"
        val qry_db = "%[qry=${qry}]%"
        val loadFromDB = MutableLiveData<List<Event>>()
        whenever(eventDao.getSearchEvent(qry_db)).doReturn(loadFromDB)

        val mockResponse = EventSearchResponse(arrayListOf(mockEvent()))
        val call = successCall(mockResponse)
        whenever(service.getSearchEvents(qry)).doReturn(call)

        val data = repository.getSearchEvent(qry)
        verify(eventDao).getSearchEvent(qry_db)
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<List<Event>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        val updatedData = MutableLiveData<List<Event>>()
        whenever(eventDao.getSearchEvent(qry_db)).doReturn(updatedData)

        loadFromDB.postValue(null)
        verify(observer).onChanged(Resource.loading(null))
        verify(service).getSearchEvents(qry)
        verify(eventDao).insertEvents(mockResponse.event)

        updatedData.postValue(mockResponse.event)
        verify(observer).onChanged(Resource.success(mockResponse.event,true))

    }

    /**
     * Scenario
     * 1. Pengecekan method terpanggil dengan baik
     */

    @Test
    fun insertEventToDb() {
        val event = mockEvent()
        repository.insertEventToDb(event)
        verify(eventDao).insert(event)
    }

    @Test
    fun getAllFavoriteNextEventInDb() {
        val idLeague = 1
        repository.getAllFavoriteNextEventInDb(idLeague)
        verify(eventDao).getFavoriteNextEvents(idLeague,1)
    }

    @Test
    fun getAllFavoritePrevEventInDb() {
        val idLeague = 1
        repository.getAllFavoritePrevEventInDb(idLeague)
        verify(eventDao).getFavoritePastEvents(idLeague,1)
    }

    @Test
    fun getEventByIdInDb() {
        val idLeague = 1
        repository.getEventByIdInDb(idLeague)
        verify(eventDao).getEventById(idLeague)
    }

    @Test
    fun updateEventInDb() {
        val event = mockEvent()
        repository.updateEventInDb(event)
        verify(eventDao).update(event)
    }
}