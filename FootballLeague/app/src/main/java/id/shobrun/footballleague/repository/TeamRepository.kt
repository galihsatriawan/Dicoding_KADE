package id.shobrun.footballleague.repository

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.TeamApi
import id.shobrun.footballleague.mapper.TeamResponseMapper
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.models.network.TeamsResponse
import id.shobrun.footballleague.room.TeamDao
import timber.log.Timber
import javax.inject.Inject

class TeamRepository @Inject constructor(val appExecutors: AppExecutors,val webservice: TeamApi, val teamDao: TeamDao) :Repository{
    companion object{
        val TAG = TeamRepository.javaClass.name
    }
    fun loadTeamDetailById(idTeam : Int) : LiveData<Resource<Team>> {
        return object : NetworkBoundRepository<Team,TeamsResponse, TeamResponseMapper>(appExecutors){
            override fun saveFetchData(items: TeamsResponse) {
                val team = items.teams[0]
                teamDao.insert(team)
            }

            override fun shouldFetch(data: Team?): Boolean {
                return data == null || data.teamName.isEmpty()
            }

            override fun loadFromDb(): LiveData<Team> {
                return teamDao.getTeamById(idTeam)
            }

            override fun fetchService(): LiveData<ApiResponse<TeamsResponse>> {
                return webservice.getTeamById(idTeam)
            }

            override fun mapper(): TeamResponseMapper {
                return TeamResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG fetch failed team $message")
            }
        }.asLiveData()
    }
}