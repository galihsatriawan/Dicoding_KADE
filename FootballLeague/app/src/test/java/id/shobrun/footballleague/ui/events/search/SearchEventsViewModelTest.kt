package id.shobrun.footballleague.ui.events.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.shobrun.footballleague.api.ApiUtil
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.models.network.EventSearchResponse
import id.shobrun.footballleague.models.network.EventsResponse
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.ui.events.previous.PreviousEventViewModel
import id.shobrun.footballleague.utils.LiveDataTestUtil.getValue
import id.shobrun.footballleague.utils.MockTestUtil
import junit.framework.Assert.assertNotNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchEventsViewModelTest{


    val eventRepository= mock<EventRepository>()
    lateinit var viewModel: SearchEventsViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = SearchEventsViewModel(eventRepository)
    }
    /**
     * Scenario
     * Pengecekan apakah method search pada repository terpanggil dengan baik
     */
    @Test
    fun getSearchEventLiveData() {
        val qry = "event"

        val data = viewModel.eventLiveData
        val observer = mock<Observer<Resource<List<Event>>>>()
        data.observeForever(observer)

        assertNotNull(eventRepository)
        assertNotNull(viewModel.filterLiveData)

        viewModel.postFilter(qry)
        verify(eventRepository).getSearchEvent(qry)

    }
}