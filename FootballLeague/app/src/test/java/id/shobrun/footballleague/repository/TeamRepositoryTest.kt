package id.shobrun.footballleague.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.shobrun.footballleague.api.ApiUtil.successCall
import id.shobrun.footballleague.api.TeamApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.models.network.TeamsResponse
import id.shobrun.footballleague.room.TeamDao
import id.shobrun.footballleague.utils.InstantAppExecutors
import id.shobrun.footballleague.utils.MockTestUtil.Companion.mockTeam
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TeamRepositoryTest {
    private val teamDao = mock<TeamDao>()
    private val service = mock<TeamApi>()
    private lateinit var repository: TeamRepository
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        this.repository = TeamRepository(InstantAppExecutors(), service, teamDao)
    }

    /**
     * Scenario
     * 1. Data Detail Team pertama diambil dari DB
     * 2. Hasil pertama dari Resource adalah Loading
     * 3. Kemudian akan dilakukan pengambilan data dari Rest API
     * 4. Setelah itu simpan seluruh data yang berhasil diambil ke DB
     * 5. Setelah itu value Resource akan berubah menjadi Success
     */
    @Test
    fun loadTeamDetailById() {
        val idTeam = 1
        val loadFromDB = MutableLiveData<Team>()
        whenever(teamDao.getTeamById(idTeam)).doReturn(loadFromDB)

        val mockResponse = TeamsResponse(arrayListOf(mockTeam()))
        val call = successCall(mockResponse)
        whenever(service.getTeamById(idTeam)).doReturn(call)

        val data = repository.loadTeamDetailById(idTeam)
        verify(teamDao).getTeamById(idTeam)
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<Team>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)

        val updateData = MutableLiveData<Team>()
        whenever(teamDao.getTeamById(idTeam)).doReturn(updateData)

        loadFromDB.postValue(null)
        verify(observer).onChanged(Resource.loading(null))
        verify(service).getTeamById(idTeam)
        verify(teamDao).insert(mockResponse.teams[0])

        updateData.postValue(mockResponse.teams[0])
        verify(observer).onChanged(Resource.success(mockResponse.teams[0], true))

    }
}