package id.shobrun.footballleague.repository

import androidx.lifecycle.LiveData
import id.shobrun.footballleague.AppExecutors
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.TeamRecordApi
import id.shobrun.footballleague.models.entity.TeamRecord
import id.shobrun.footballleague.models.network.TeamRecordsResponse
import id.shobrun.footballleague.room.TeamRecordDao
import timber.log.Timber
import javax.inject.Inject

class TeamRecordRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: TeamRecordApi,
    private val localDB: TeamRecordDao
) {
    companion object {
        val TAG = this.javaClass.name
    }

    fun getTeamRecordsByIdLeague(idLeague: Int, season: Int) =
        object : NetworkBoundRepository<List<TeamRecord>, TeamRecordsResponse>(appExecutors) {
            override fun saveFetchData(items: TeamRecordsResponse) {
                val teams = items.table
                var rank = 1
                if (teams != null) {
                    for (i in teams.indices) {
                        teams[i].idLeague = idLeague
                        teams[i].rank = rank++
                    }
                    localDB.inserts(teams)
                }
            }

            override fun shouldFetch(data: List<TeamRecord>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun loadFromDb(): LiveData<List<TeamRecord>> {
                return localDB.getStandingByIdLeague(idLeague)
            }

            override fun fetchService(): LiveData<ApiResponse<TeamRecordsResponse>> {
                val data = apiService.getStandingLeague(idLeague, season)
                Timber.d("$TAG ${data?.value?.body?.table?.size}")
                return data
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG $message")
            }
        }.asLiveData()
}