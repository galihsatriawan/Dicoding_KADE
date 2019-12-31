package id.shobrun.footballleague.ui.events.favorite.previous

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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FavoritePreviousEventViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val eventDao = mock<EventDao>()
    private val eventApi = mock<EventApi>()

    private lateinit var repository: EventRepository
    private lateinit var viewModel: FavoritePreviousEventViewModel

    @Before
    fun setUp() {
        repository = EventRepository(InstantAppExecutors(), eventApi, eventDao)
        viewModel = FavoritePreviousEventViewModel(repository)
    }

    /**
     * Scenario
     * 1. Apakah get data dan observable nya berjalan dengan baik
     */
    @Test
    fun getPreviousEventLiveData() {
        val idLeague = 1
        val loadFromDb = MutableLiveData<List<Event>>()
        whenever(eventDao.getFavoritePastEvents(idLeague, 1)).doReturn(loadFromDb)

        val data = viewModel.previousEventLiveData
        val observer = mock<Observer<List<Event>>>()
        data.observeForever(observer)

        viewModel.postLeagueId(idLeague)

        verify(eventDao).getFavoritePastEvents(idLeague, 1)

        loadFromDb.postValue(mockEventList())
        verify(observer).onChanged(
            mockEventList()
        )
    }
}