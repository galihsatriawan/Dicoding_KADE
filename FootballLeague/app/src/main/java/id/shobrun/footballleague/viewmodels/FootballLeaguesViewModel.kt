package id.shobrun.footballleague.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.iviews.IFootballLeaguesFragment

class FootballLeaguesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var leagues = MutableLiveData<ArrayList<League>>()
    lateinit var context: Context
    lateinit var view : IFootballLeaguesFragment

    fun setAppView(context: Context, view:IFootballLeaguesFragment){
        this.context = context
        this.view = view
    }
    internal fun setLeagues(){
        var leagues: ArrayList<League> = ArrayList()
        val id = context.resources.getIntArray(R.array.league_id)
        val name = context.resources.getStringArray(R.array.league_name)
        val image = context.resources.obtainTypedArray(R.array.league_banner)
        val desc = context.resources.getStringArray(R.array.league_desc)
        for(i in name.indices){
            leagues.add(League(id[i],name[i],image.getResourceId(i,0),desc[i]))
        }
        image.recycle()
        this.leagues.postValue(leagues)
    }


    internal fun getLeagues() : LiveData<ArrayList<League>> = leagues

}
