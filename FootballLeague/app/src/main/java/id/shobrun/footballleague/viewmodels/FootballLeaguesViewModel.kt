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
    lateinit var view : IFootballLeaguesFragment

    fun setAppView(view:IFootballLeaguesFragment){
        this.view = view
    }
    internal fun setLeagues(){
        val leagues: ArrayList<League> = ArrayList()
        this.leagues.postValue(leagues)
    }


    internal fun getLeagues() : LiveData<ArrayList<League>> = leagues

}
