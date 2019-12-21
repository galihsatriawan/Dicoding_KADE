package id.shobrun.footballleague.ui.events.previous

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import id.shobrun.footballleague.api.ApiUtil.successCall
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.repository.LeagueRepository
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.room.LeagueDao
import id.shobrun.footballleague.ui.leagues.detail.DetailLeagueViewModel
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockEventList
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PreviousEventViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PreviousEventViewModel

    private lateinit var eventRepository: EventRepository
    private val eventDao = mock<EventDao>()
    private val eventApi = mock<EventApi>()

    @Before
    fun setUp() {
        eventRepository = EventRepository(eventApi,eventDao)
        viewModel = PreviousEventViewModel(eventRepository)
    }
    /**
     * Scenario
     * Pengecekan apakah observable data berjalan dengan baik
     */
    @Test
    fun getPreviousEventLiveData() {
        val idLeague = 1
        val loadFromDB = MutableLiveData<List<Event>>()
        whenever(eventDao.getPastEvents(idLeague)).doReturn(loadFromDB)

        val mockResponse = EventsResponse(mockEventList())
        val call = successCall(mockResponse)
        whenever(eventApi.getPastEvents(idLeague)).doReturn(call)

        val data = viewModel.previousEventLiveData
        val observer = mock<Observer<Resource<List<Event>>>>()
        data.observeForever(observer)

        viewModel.postLeagueId(idLeague)
        viewModel.post_LeagueId("test")
        verify(eventDao).getPastEvents(idLeague)

        loadFromDB.postValue(mockEventList())
        verify(observer).onChanged(
            Resource.success(viewModel.previousEventLiveData.value?.data,true)
        )
    }
}