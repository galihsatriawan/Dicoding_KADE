package id.shobrun.footballleague.views.leagues

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.iviews.IDetailLeagueActivity
import javax.inject.Inject

class DetailLeagueViewModel @Inject constructor() : ViewModel(){
    companion object{
        val TAG = DetailLeagueViewModel::class.simpleName
    }
    private var league : MutableLiveData<League> = MutableLiveData()
    lateinit var view : IDetailLeagueActivity
    init {
        Log.d(TAG,"VM work")
    }
    fun setLeague(league: League?){
        this.league.postValue(league)
    }
    fun getLeague():LiveData<League> = league

}