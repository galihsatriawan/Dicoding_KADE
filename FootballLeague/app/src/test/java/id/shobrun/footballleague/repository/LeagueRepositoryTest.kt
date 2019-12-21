package id.shobrun.footballleague.repository

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.room.LeagueDao
import org.junit.Before
import org.junit.Test

import id.shobrun.footballleague.api.ApiUtil.successCall
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.network.LeaguesResponse
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockLeague
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class LeagueRepositoryTest  {
    val application = mock<Application>()
    private lateinit var repository: LeagueRepository
    private val service = mock<LeagueApi>()
    private val leagueDao = mock<LeagueDao>()
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUp() {
        this.repository = LeagueRepository(service,leagueDao, application)
    }
    /**
     * Scenario
     * 1. Data Detail League pertama diambil dari DB
     * 2. Hasil pertama dari Resource adalah Loading
     * 3. Kemudian akan dilakukan pengambilan data dari Rest API
     * 4. Setelah itu simpan seluruh data yang berhasil diambil ke DB
     * 5. Setelah itu value Resource akan berubah menjadi Success
     */

    @Test
    fun loadLeagueDetail() {
        val idLeague = 1
        val loadFromDB = MutableLiveData<League>()
        whenever(leagueDao.getLeagueById(idLeague)).doReturn(loadFromDB)

        val mockResponse = LeaguesResponse(arrayListOf(mockLeague()))
        val call = successCall(mockResponse)
        whenever(service.getLeagueById(idLeague)).doReturn(call)

        val data = repository.loadLeagueDetail(idLeague)
        verify(leagueDao).getLeagueById(idLeague)
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<League>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)

        val updateData = MutableLiveData<League>()
        whenever(leagueDao.getLeagueById(idLeague)).doReturn(updateData)

        loadFromDB.postValue(null)
        verify(observer).onChanged(Resource.loading(null))
        verify(service).getLeagueById(idLeague)
        verify(leagueDao).insert(mockResponse.leagues[0])

        updateData.postValue(mockResponse.leagues[0])
        verify(observer).onChanged(Resource.success(mockResponse.leagues[0],true))

    }
}