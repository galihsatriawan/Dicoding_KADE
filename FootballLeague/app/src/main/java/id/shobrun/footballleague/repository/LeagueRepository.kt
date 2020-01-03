package id.shobrun.footballleague.repository


import android.app.Application
import androidx.lifecycle.LiveData
import id.shobrun.footballleague.AppExecutors

import id.shobrun.footballleague.R
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.models.network.LeaguesResponse
import id.shobrun.footballleague.room.LeagueDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LeagueRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: LeagueApi,
    private val localDB: LeagueDao,
    private val application: Application
) : Repository {
    companion object {
        val TAG = LeagueRepository.javaClass.name
    }

    fun loadLeagueDetail(id: Int): LiveData<Resource<League>> {
        return object : NetworkBoundRepository<League, LeaguesResponse>(appExecutors) {
            override fun saveFetchData(items: LeaguesResponse) {
                localDB.insert(items.leagues[0])

            }

            override fun shouldFetch(data: League?): Boolean {
                return data == null || data.name.isEmpty()
            }

            override fun loadFromDb(): LiveData<League> {
                val league = localDB.getLeagueById(id)

                return (league)
            }

            override fun fetchService(): LiveData<ApiResponse<LeaguesResponse>> {
                Timber.d("$TAG id Fetch : $id")
                return apiService.getLeagueById(id)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed $message")
            }

        }.asLiveData()


    }

    fun getResourceLeagues(): List<League> {
        val leagues: ArrayList<League> = ArrayList()
        with(application.applicationContext) {
            val id = resources.getIntArray(R.array.league_id)
            val name = resources.getStringArray(R.array.league_name)
            val image = resources.obtainTypedArray(R.array.league_banner)
            val desc = resources.getStringArray(R.array.league_desc)
            for (i in name.indices) {
                leagues.add(
                    League(
                        id[i],
                        name[i],
                        "",
                        "",
                        image.getResourceId(i, 0),
                        desc[i]
                    )
                )
            }
            image.recycle()
        }
        Timber.d(TAG, "${leagues.size}")
        return leagues
    }

}