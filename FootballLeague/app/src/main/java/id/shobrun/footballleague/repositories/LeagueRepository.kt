package id.shobrun.footballleague.repositories


import android.app.Application
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.models.utils.resultLiveData
import id.shobrun.footballleague.repositories.remote.LeagueRemoteDataSource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeagueRepository @Inject constructor(private val remote : LeagueRemoteDataSource,private val application: Application) {
    companion object{
        val TAG = LeagueRepository.javaClass.name
    }
    fun getLeagueById(id : Int) = resultLiveData(
        databaseQuery = {},
        networkCall = {remote.fetchDataLeagueById(id)},
        saveCallResult = {}
    )
    fun getResourceLeagues() : List<League> {
        var leagues: ArrayList<League> = ArrayList()
        with(application.applicationContext){
            val id = resources.getIntArray(R.array.league_id)
            val name = resources.getStringArray(R.array.league_name)
            val image = resources.obtainTypedArray(R.array.league_banner)
            val desc = resources.getStringArray(R.array.league_desc)
            for(i in name.indices){
                leagues.add(League(id[i],name[i],"",image.getResourceId(i,0),desc[i]))
            }
            image.recycle()
        }

        Timber.d(TAG, "${leagues.size}")
        return leagues
    }

}