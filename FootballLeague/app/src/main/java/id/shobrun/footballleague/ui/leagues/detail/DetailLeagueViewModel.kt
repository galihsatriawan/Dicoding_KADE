package id.shobrun.footballleague.views.leagues

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.views.iviews.IDetailLeagueActivity
import javax.inject.Inject

class DetailLeagueViewModel @Inject constructor(val repository: LeagueRepository) : ViewModel(){
    companion object{
        val TAG = this.javaClass.name
    }
    private var _league : MutableLiveData<League> = MutableLiveData()
    var league = _league
    init {
        Log.d(TAG,"VM work")
    }
    fun setLeague(league: League?){
        _league.value =league
    }

}