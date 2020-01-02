package id.shobrun.footballleague.repository

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.TeamApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.Team
import id.shobrun.footballleague.models.network.TeamsResponse
import id.shobrun.footballleague.repository.utils.ITeamLocalDB
import id.shobrun.footballleague.room.TeamDao
import timber.log.Timber
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: TeamApi,
    private val localDB: TeamDao
) : Repository, ITeamLocalDB {
    companion object {
        val TAG = TeamRepository.javaClass.name
    }
    fun loadListTeamByLeagueId(idLeague: Int) = object : NetworkBoundRepository<List<Team>,TeamsResponse>(appExecutors){
        override fun saveFetchData(items: TeamsResponse) {
            val teams = items.teams
            if(teams!=null){
                localDB.inserts(teams)
            }
        }

        override fun shouldFetch(data: List<Team>?): Boolean {
            return data.isNullOrEmpty()
        }

        override fun loadFromDb(): LiveData<List<Team>> {
            return localDB.getAllTeamsByLeagueId(idLeague)
        }

        override fun fetchService(): LiveData<ApiResponse<TeamsResponse>> {
            return apiService.getTeamsByLeagueId(idLeague)
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$TAG $message")
        }
    }.asLiveData()

    fun loadSearchTeams(qry: String) = object :  NetworkBoundRepository<List<Team>,TeamsResponse>(appExecutors) {
        override fun saveFetchData(items: TeamsResponse) {
            val teams = items.teams
            if(teams!=null){
                Timber.d("$TAG ${teams.size}")
                for(t in teams){
                    t.tags = "[q=${qry}]"
                }
                localDB.inserts(teams)
            }
        }

        override fun shouldFetch(data: List<Team>?): Boolean {
            return data.isNullOrEmpty()
        }

        override fun loadFromDb(): LiveData<List<Team>> {
            return localDB.getSearchTeams("[qry={$qry}]")
        }

        override fun fetchService(): LiveData<ApiResponse<TeamsResponse>> {
            val teams = apiService.getSearchTeam(qry)
            Timber.d("$TAG ${teams?.value?.body?.teams?.size}")
            return teams
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$TAG $message")
        }
    }.asLiveData()

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

    override fun insertTeamInDB(team: Team) {
        localDB.insert(team)
    }

    override fun getAllFavoriteTeams(): LiveData<List<Team>> {
        return localDB.getAllFavoriteTeam(1)
    }

    override fun getTeamById(idTeam: Int): LiveData<Team> {
        return localDB.getTeamById(idTeam)
    }

    override fun updateTeamInDB(team: Team): Int {
        return localDB.update(team)
    }
}