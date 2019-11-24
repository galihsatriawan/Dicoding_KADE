package id.shobrun.footballleague.views.leagues

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.views.iviews.IDetailLeagueActivity

class DetailLeagueViewModel : ViewModel(){
    private var league : MutableLiveData<League> = MutableLiveData()
    lateinit var view : IDetailLeagueActivity
    fun setAppView(view:IDetailLeagueActivity){
        this.view = view
    }
    fun setLeague(league: League?){
        this.league.postValue(league)
    }
    fun getLeague():LiveData<League> = league

}