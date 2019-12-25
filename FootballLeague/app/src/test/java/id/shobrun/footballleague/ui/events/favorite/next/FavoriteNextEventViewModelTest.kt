package id.shobrun.footballleague.ui.events.favorite.next

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import id.shobrun.footballleague.api.EventApi
import id.shobrun.footballleague.models.entity.Event
import id.shobrun.footballleague.repository.EventRepository
import id.shobrun.footballleague.room.EventDao
import id.shobrun.footballleague.utils.InstantAppExecutors
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockEventList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteNextEventViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteNextEventViewModel
    private lateinit var repository: EventRepository
    private val eventDao = mock<EventDao>()
    private val eventApi = mock<EventApi>()

    @Before
    fun setUp() {
        repository = EventRepository(InstantAppExecutors(), eventApi,eventDao)
        viewModel = FavoriteNextEventViewModel(repository)
    }

    /**
     * Scenario
     * 1. Apakah Observable berjalan dengan baik
     */
    @Test
    fun getNextEventLiveData() {
        val idLeague = 1
        val loadFromDb = MutableLiveData<List<Event>>()
        whenever(eventDao.getFavoriteNextEvents(idLeague,1)).doReturn(loadFromDb)

        val data = viewModel.nextEventLiveData
        val observer = mock<Observer<List<Event>>>()
        data.observeForever(observer)

        viewModel.postLeagueId(idLeague)

        verify(eventDao).getFavoriteNextEvents(idLeague,1)

        loadFromDb.postValue(mockEventList())
        verify(observer).onChanged(
            mockEventList()
        )
    }
}