package id.shobrun.footballleague.repositories


import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.shobrun.footballleague.R
import id.shobrun.footballleague.api.LeagueApi
import id.shobrun.footballleague.models.League
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeagueRepository @Inject constructor(private val remote : LeagueApi,private val application: Application) {
    companion object{
        val TAG = LeagueRepository.javaClass.name
    }
    init {
    }
    fun getLeagues() : List<League> {
        var leagues: ArrayList<League> = ArrayList()
        with(application.applicationContext){
            val id = resources.getIntArray(R.array.league_id)
            val name = resources.getStringArray(R.array.league_name)
            val image = resources.obtainTypedArray(R.array.league_banner)
            val desc = resources.getStringArray(R.array.league_desc)
            for(i in name.indices){
                leagues.add(League(id[i],name[i],image.getResourceId(i,0),desc[i]))
            }
            image.recycle()
        }
        Log.d(TAG, "${leagues.size}")
        return leagues
    }
}