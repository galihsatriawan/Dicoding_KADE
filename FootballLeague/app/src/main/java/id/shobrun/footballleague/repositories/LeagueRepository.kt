package id.shobrun.footballleague.repositories


import android.app.Application
import android.app.Person
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import id.shobrun.footballleague.R
import id.shobrun.footballleague.api.ApiResponse
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.mapper.LeagueDetailResponseMapper
import id.shobrun.footballleague.models.Resource
import id.shobrun.footballleague.models.entity.League
import id.shobrun.footballleague.room.LeagueDao
import id.shobrun.footballleague.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LeagueRepository @Inject constructor(private val webservice : LeagueApi,private val leagueDao : LeagueDao, private val application: Application) : Repository{
    companion object{
        val TAG = LeagueRepository.javaClass.name
    }
    fun loadLeagueDetail(id : Int) : LiveData<Resource<League>> {
        return object : NetworkBoundRepository< League ,League ,LeagueDetailResponseMapper>(){
            override fun saveFetchData(items: League) {
                leagueDao.updateLeague(items)

            }

            override fun shouldFetch(data: League?): Boolean {
                Timber.d("$TAG ${data?._id}")
                return data==null || data?.description.isEmpty()
            }

            override fun loadFromDb(): LiveData<League> {
                val league = leagueDao.getLeagueById(id)

                return (league)
            }

            override fun fetchService(): LiveData<ApiResponse<League>> {
                return webservice.getLeagueById(id)
            }

            override fun mapper(): LeagueDetailResponseMapper {
                return LeagueDetailResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed $message")
            }

        }.asLiveData()


    }
    fun getResourceLeagues() : List<League> {
        var leagues: ArrayList<League> = ArrayList()
        with(application.applicationContext){
            val id = resources.getIntArray(R.array.league_id)
            val name = resources.getStringArray(R.array.league_name)
            val image = resources.obtainTypedArray(R.array.league_banner)
            val desc = resources.getStringArray(R.array.league_desc)
            for(i in name.indices){
                leagues.add(
                    League(
                        id[i],
                        name[i],
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