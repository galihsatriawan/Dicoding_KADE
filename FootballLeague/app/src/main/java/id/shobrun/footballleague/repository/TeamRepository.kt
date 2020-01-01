package id.shobrun.footballleague.repository

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.TeamApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.models.network.TeamsResponse
import id.shobrun.footballleague.room.TeamDao
import timber.log.Timber
import javax.inject.Inject

class TeamRepository @Inject constructor(
    val appExecutors: AppExecutors,
    val apiService: TeamApi,
    val localDB: TeamDao
) : Repository {
    companion object {
        val TAG = TeamRepository.javaClass.name
    }

    fun loadTeamDetailById(idTeam: Int): LiveData<Resource<Team>> {
        return object : NetworkBoundRepository<Team, TeamsResponse>(appExecutors) {
            override fun saveFetchData(items: TeamsResponse) {
                val team = items.teams[0]
                localDB.insert(team)
            }

            override fun shouldFetch(data: Team?): Boolean {
                return data == null || data.teamName.isEmpty()
            }

            override fun loadFromDb(): LiveData<Team> {
                return localDB.getTeamById(idTeam)
            }

            override fun fetchService(): LiveData<ApiResponse<TeamsResponse>> {
                return apiService.getTeamById(idTeam)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG fetch failed team $message")
            }
        }.asLiveData()
    }
}