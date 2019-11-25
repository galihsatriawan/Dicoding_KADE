package id.shobrun.footballleague.views.leagues.fragments

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import id.shobrun.footballleague.R
import id.shobrun.footballleague.models.League
import id.shobrun.footballleague.repositories.LeagueRepository
import id.shobrun.footballleague.views.iviews.IFootballLeaguesFragment
import javax.inject.Inject

class FootballLeaguesViewModel @Inject constructor( ) : ViewModel() {
    // TODO: Implement the ViewModel
    companion object{
        val TAG = FootballLeaguesViewModel.javaClass.name
    }
    private var _leagues = MutableLiveData<ArrayList<League>>()
    var leagues:LiveData<ArrayList<League>> = _leagues
    init {
        Log.d(TAG, "VM work")
    }
    val empty:LiveData<Boolean> = Transformations.map(_leagues){
        it.isEmpty()
    }
    init {

    }
}
