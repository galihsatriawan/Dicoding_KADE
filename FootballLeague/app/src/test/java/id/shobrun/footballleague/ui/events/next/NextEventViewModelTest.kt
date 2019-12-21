package id.shobrun.footballleague.ui.events.next

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import id.shobrun.footballleague.api.ApiUtil.successCall
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockEventList
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class NextEventViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NextEventViewModel
    private lateinit var repository: EventRepository
    private val eventDao = mock<EventDao>()
    private val eventApi = mock<EventApi>()

    @Before
    fun setUp() {
        repository = EventRepository(eventApi,eventDao)
        viewModel = NextEventViewModel(repository)
    }

    /**
     * Scenario
     * Pengecekan apakah observable data berjalan dengan baik
     */
    @Test
    fun getNextEventLiveData() {
        val idLeague = 1
        val loadFromDB = MutableLiveData<List<Event>>()
        whenever(eventDao.getNextEvents(idLeague)).doReturn(loadFromDB)

        val mockResponse = EventsResponse(mockEventList())
        val call = successCall(mockResponse)
        whenever(eventApi.getNextEvents(idLeague)).doReturn(call)

        val data = viewModel.nextEventLiveData
        val observer = mock<Observer<Resource<List<Event>>>>()
        data.observeForever(observer)

        viewModel.postLeagueId(idLeague)
        verify(eventDao).getNextEvents(idLeague)

        loadFromDB.postValue(mockEventList())
        verify(observer).onChanged(
            Resource.success(mockEventList(),true)
        )
    }
}