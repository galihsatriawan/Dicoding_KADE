package id.shobrun.footballleague.ui.events.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchEventsViewModelTest {


    private val eventRepository = mock<EventRepository>()
    private lateinit var viewModel: SearchEventsViewModel

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