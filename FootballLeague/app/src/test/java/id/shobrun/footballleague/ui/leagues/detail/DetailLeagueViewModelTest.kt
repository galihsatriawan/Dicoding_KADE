package id.shobrun.footballleague.ui.leagues.detail

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import id.shobrun.footballleague.api.ApiUtil.successCall
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.network.LeaguesResponse
import id.shobrun.footballleague.repository.LeagueRepository
import id.shobrun.footballleague.room.LeagueDao
import id.shobrun.footballleague.utils.InstantAppExecutors
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockLeague
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailLeagueViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailLeagueViewModel

    private lateinit var leagueRepository: LeagueRepository
    private val leagueDao = mock<LeagueDao>()
    private val leagueApi = mock<LeagueApi>()
    private val application = mock<Application>()

    @Before
    fun setUp() {
        leagueRepository = LeagueRepository(InstantAppExecutors(), leagueApi,leagueDao,application)
        viewModel = DetailLeagueViewModel(leagueRepository)
    }

    /**
     * Scenario
     * Pengecekan apakah observable data berjalan dengan baik
     */
    @Test
    fun getLeagueLiveData() {
        val idLeague = 1
        val loadFromDB = MutableLiveData<League>()
        whenever(leagueDao.getLeagueById(idLeague)).doReturn(loadFromDB)

        val mockResponse = LeaguesResponse(arrayListOf(mockLeague()))
        val call = successCall(mockResponse)
        whenever(leagueApi.getLeagueById(idLeague)).doReturn(call)

        val data = viewModel.leagueLiveData
        val observer = mock<Observer<Resource<League>>>()
        data.observeForever(observer)

        viewModel.postLeagueId(idLeague)
        verify(leagueDao).getLeagueById(idLeague)

        val mockLeague = mockLeague()
        loadFromDB.postValue(mockLeague)
        verify(observer).onChanged(
            Resource.success(viewModel.leagueLiveData.value?.data,true)
        )
    }
}